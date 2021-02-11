package com.afkar.controllers.story;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.dao.UserDAO;
import com.afkar.models.Story;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class SavedStories extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userLoggedIn(req.getSession())){

            String page = req.getParameter("page");
            long page_count;
            if(page == null){
                page_count = 1;
            }else{
                page_count = Long.valueOf(page);

                if(page_count < 1) page_count = 1;
            }

            DAOFactory daoFactory = DAOFactory.getInstance();
            StoryDAO storyDAO = daoFactory.getStoryDao();
            ArrayList<Story> stories;

            User user = (User) req.getSession().getAttribute("user");

            stories = storyDAO.findSavedStories(user, page_count);

            req.setAttribute("stories", stories);
            req.setAttribute("page", page_count);



            // User logged in
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/saved_stories.jsp").forward(req, resp);
            return;
        }
        // User logged out
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/saved_stories.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        if(uuid == null){
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if(userLoggedIn(req.getSession())){
            User user = (User) req.getSession().getAttribute("user");

            DAOFactory daoFactory = DAOFactory.getInstance();
            StoryDAO storyDAO = daoFactory.getStoryDao();

            storyDAO.saveStory(user, uuid);


        }
        resp.sendRedirect(req.getContextPath() + "/story?uuid=" + uuid);
        return;
    }
}
