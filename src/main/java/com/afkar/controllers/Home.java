package com.afkar.controllers;

import com.afkar.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class Home extends HttpServlet {
    String username = null;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(Utils.hash(Utils.generateUUID()));

        if(userLoggedIn(req.getSession())){
            // User logged in
            this.getServletContext().getRequestDispatcher("/home_logged_in.jsp").forward(req, resp);
        }
        // User logged out
        this.getServletContext().getRequestDispatcher("/home_logged_out.jsp").forward(req, resp);


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
