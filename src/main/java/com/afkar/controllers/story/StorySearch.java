package com.afkar.controllers.story;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.models.Story;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class StorySearch  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userLoggedIn(req.getSession())){


            String search_text = req.getParameter("search_text");
            if(search_text != null) {
                search_text = search_text.trim();

                String page = req.getParameter("page");
                long page_count;
                if (page == null) {
                    page_count = 1;
                } else {
                    page_count = Long.valueOf(page);

                    if (page_count < 1) page_count = 1;
                }

                DAOFactory daoFactory = DAOFactory.getInstance();
                StoryDAO storyDAO = daoFactory.getStoryDao();
                ArrayList<Story> stories;

                User user = (User) req.getSession().getAttribute("user");

                stories = storyDAO.searchStories(search_text, page_count);

                req.setAttribute("stories", stories);
                req.setAttribute("page", page_count);
                req.setAttribute("search_text", search_text);


                // User logged in
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/search.jsp").forward(req, resp);
                return;
            }
        }
        // User logged out
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/saved_stories.jsp").forward(req, resp);

    }

}
