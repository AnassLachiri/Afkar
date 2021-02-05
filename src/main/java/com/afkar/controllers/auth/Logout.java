package com.afkar.controllers.auth;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.UserDAO;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class Logout extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(userLoggedIn(req.getSession())){
            // User connected
            req.getSession().removeAttribute("username");
            req.getSession().removeAttribute("user");
        }
        // User not connected
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(userLoggedIn(req.getSession())){
            // User connected
            req.getSession().removeAttribute("username");
            req.getSession().removeAttribute("user");
        }
        // User not connected
        resp.sendRedirect(req.getContextPath() + "/");

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
