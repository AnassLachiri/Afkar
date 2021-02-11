package com.afkar.controllers.user;

import com.afkar.Utils;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.UserDAO;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class Follow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if(userLoggedIn(req.getSession())){
            if(username!=null && !username.isEmpty()){
                User user = (User) req.getSession().getAttribute("user");
                if(user != null) {

                    DAOFactory daoFactory = DAOFactory.getInstance();
                    UserDAO userDAO = daoFactory.getUserDao();
                    userDAO.follow(user, username);
                    return;
                }
            }
        }
        return;
    }
}
