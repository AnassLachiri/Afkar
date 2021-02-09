package com.afkar.controllers.story;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class StoryDelete extends HttpServlet {
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

            DAOFactory daoFactory = DAOFactory.getInstance();
            StoryDAO storyDAO = daoFactory.getStoryDao();
            storyDAO.delete(uuid);

            //resp.sendRedirect(String.valueOf(req.getRequestURL()));
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        // User logged out
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
