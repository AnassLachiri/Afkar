package com.afkar.controllers.story;

import com.afkar.Utils;
import com.afkar.dao.CommentDAO;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.models.Story;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class StoryCreate extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userLoggedIn(req.getSession())){
            // User logged in
            this.getServletContext().getRequestDispatcher("/story_create.jsp").forward(req, resp);
            return;
        }
        // User logged out
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String subtitle = req.getParameter("subtitle");
        String content = req.getParameter("content");
        String keywords = req.getParameter("keywords");

        if(title == null || subtitle == null || content == null || keywords == null){
            req.setAttribute("story_create_error", "All inputs should be filled!!");
            this.getServletContext().getRequestDispatcher("/story_create.jsp").forward(req, resp);
            return;
        }

        if(userLoggedIn(req.getSession())){
            // User logged in
            User user = (User) req.getSession().getAttribute("user");

            if(user!=null){

                Story story = new Story(0);
                story.setUser_id(user.getId());
                story.setUuid(Utils.generateUUID());
                story.setTotal_likes(0);
                story.setTitle(title);
                story.setSubtitle(subtitle);
                story.setKeywords(keywords);
                story.setContent(content);

                DAOFactory daoFactory = DAOFactory.getInstance();
                StoryDAO storyDAO = daoFactory.getStoryDao();
                storyDAO.create(story);

                //resp.sendRedirect(String.valueOf(req.getRequestURL()));
                resp.sendRedirect(req.getContextPath() + "/story?uuid=" + story.getUuid());
                return;
            }

            System.out.println("User attribute in the session  returns null!!!");
        }
        // User logged out
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
