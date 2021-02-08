package com.afkar.controllers.auth;

import com.afkar.Utils;
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


public class Register extends HttpServlet {
    public String username = null;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(userLoggedIn(req.getSession())){
            // User connected
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        // User not connected
        this.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");


        if(username!=null && !username.isEmpty() &&
                email!=null && !email.isEmpty() &&
                password1!=null && !password1.isEmpty() &&
                password2!=null && !password2.isEmpty()){
            if(!password1.equals(password2)){
                req.getSession().setAttribute("register_error", "Passwords should be the same. Try again!");
                this.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            password1 = Utils.hash(password1);

            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDao();
            User user = null;
            user = userDAO.find(username);
            if(user != null){ //
                req.getSession().setAttribute("register_error", "Username already used. Try another one!");
                this.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            user = new User(0);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password1);

            userDAO.create(user);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("username", user.getUsername());

            resp.sendRedirect(req.getContextPath() + "/");
            return;

        }
        req.getSession().setAttribute("register_error", "Something went wrong!!");
        this.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}