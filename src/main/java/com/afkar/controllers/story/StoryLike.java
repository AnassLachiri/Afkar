package com.afkar.controllers.story;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class StoryLike extends HttpServlet {
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

            storyDAO.likeStory(user, uuid);


        }
        resp.sendRedirect(req.getContextPath() + "/story?uuid=" + uuid);
        return;
    }
}
