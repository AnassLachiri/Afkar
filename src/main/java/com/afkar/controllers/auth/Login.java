package com.afkar.controllers.auth;

import com.afkar.Utils;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.UserDAO;
import com.afkar.dao.UserDAOImpl;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.afkar.middlewares.Auth.userLoggedIn;

public class Login extends HttpServlet {
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
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();
        User user = null;
        if(username!=null && !username.isEmpty() &&
                password!=null && !password.isEmpty()){
            user = userDAO.find(username);

            //hash the password before comparing it!!
            password = Utils.hash(password);

            if(user != null){
                if (user.getPassword().equals(password)){
                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("user", user);
                    httpSession.setAttribute("username", user.getUsername());
                    resp.sendRedirect(req.getContextPath() + "/");
                    return;
                }
            }
        }
        req.getSession().setAttribute("login_error", "Something went wrong!!");
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
