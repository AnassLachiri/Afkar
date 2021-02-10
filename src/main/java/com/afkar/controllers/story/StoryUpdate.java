package com.afkar.controllers.story;

import com.afkar.Utils;
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

public class StoryUpdate extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        if(uuid == null){
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if(userLoggedIn(req.getSession())){
            Story story = null;
            DAOFactory daoFactory = DAOFactory.getInstance();
            StoryDAO storyDAO = daoFactory.getStoryDao();

            story = storyDAO.find(uuid);

            if(story == null){
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }

            User user = (User) req.getSession().getAttribute("user");

            if(user.getId() != story.getUser_id()){  // Edit other user's story
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }

            req.setAttribute("title", story.getTitle());
            req.setAttribute("subtitle", story.getSubtitle());
            req.setAttribute("content", story.getContent());
            req.setAttribute("keywords", story.getKeywords());
            req.setAttribute("uuid", story.getUuid());

            // User logged in
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/story_update.jsp").forward(req, resp);
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
        String uuid = req.getParameter("uuid");

        if(title == null || subtitle == null || content == null || keywords == null){
            req.setAttribute("story_update_error", "All inputs should be filled!!");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/story_update.jsp").forward(req, resp);
            return;
        }

        if(userLoggedIn(req.getSession())){
            // User logged in
            User user = (User) req.getSession().getAttribute("user");

            if(user!=null){

                Story story = null;

                DAOFactory daoFactory = DAOFactory.getInstance();
                StoryDAO storyDAO = daoFactory.getStoryDao();
                story = storyDAO.find(uuid);


                if(story.getUser_id() != user.getId()){
                    resp.sendRedirect(req.getContextPath() + "/");
                    return;
                }

                story.setTitle(title);
                story.setSubtitle(subtitle);
                story.setKeywords(keywords);
                story.setContent(content);

                storyDAO.update(story);


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
