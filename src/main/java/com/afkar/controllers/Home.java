package com.afkar.controllers;

import com.afkar.Utils;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.models.Story;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class Home extends HttpServlet {
    String username = null;

    @Override
    public void init() throws ServletException {
        super.init();
    }

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
            ArrayList<Story> stories = new ArrayList<Story>();



            stories = storyDAO.findAllStories(page_count);



            req.setAttribute("stories", stories);
            req.setAttribute("page", page_count);



            // User logged in
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/home_logged_in.jsp").forward(req, resp);
            return;
        }
        // User logged out
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home_logged_out.jsp").forward(req, resp);


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
