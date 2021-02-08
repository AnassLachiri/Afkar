package com.afkar.controllers.comment;

import com.afkar.dao.CommentDAO;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.UserDAO;
import com.afkar.models.Comment;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class CommentCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userLoggedIn(req.getSession())){
            // User connected

            User user = (User) req.getSession().getAttribute("user");

            if(user!=null){

                Comment comment = new Comment(0);
                comment.setTotal_likes(0);
                comment.setContent(req.getParameter("comment"));
                comment.setStory_id(Long.parseLong(req.getParameter("story_id")));
                comment.setUser_id(user.getId());

                DAOFactory daoFactory = DAOFactory.getInstance();
                CommentDAO commentDAO = daoFactory.getCommentDao();
                commentDAO.create(comment);

                resp.sendRedirect(String.valueOf(req.getRequestURL()));
                return;
            }

            System.out.println("User attribute in the session  returns null!!!");
        }
        // User not connected
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
