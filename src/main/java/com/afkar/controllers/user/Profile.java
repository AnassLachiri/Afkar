package com.afkar.controllers.user;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.models.Story;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userLoggedIn(req.getSession())){

            String page = req.getParameter("page");
            String username = req.getParameter("username");
            if(username == null){
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }
            long page_count;
            if(page == null){
                page_count = 1;
            }else{
                page_count = Long.valueOf(page);
                if(page_count < 1) page_count = 1;
            }

            DAOFactory daoFactory = DAOFactory.getInstance();
            StoryDAO storyDAO = daoFactory.getStoryDao();
            ArrayList<Story> stories = new ArrayList<Story>();

            stories = storyDAO.findProfileStories(username, page_count);

            req.setAttribute("stories", stories);
            req.setAttribute("page", page_count);
            req.setAttribute("username", username);



            // User logged in
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
            return;
        }
        // User logged out
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home_logged_out.jsp").forward(req, resp);

    }
}
