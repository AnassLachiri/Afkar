package com.afkar.controllers.comment;

import com.afkar.dao.CommentDAO;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.StoryDAO;
import com.afkar.models.Comment;
import com.afkar.models.Story;
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
        String content = req.getParameter("content");
        String story_uuid = req.getParameter("story_uuid");

        if(story_uuid == null || content == null){
            resp.sendRedirect(req.getContextPath() + "/story?uuid=" + story_uuid);
            return;
        }

        if(userLoggedIn(req.getSession())){
            // User connected

            User user = (User) req.getSession().getAttribute("user");

            if(user!=null){

                Comment comment = new Comment(0);
                comment.setContent(content);
                comment.setUser_id(user.getId());

                DAOFactory daoFactory = DAOFactory.getInstance();

                StoryDAO storyDAO = daoFactory.getStoryDao();
                Story story = storyDAO.find(story_uuid);
                comment.setStory_id(story.getId());

                CommentDAO commentDAO = daoFactory.getCommentDao();
                commentDAO.create(comment);

                resp.sendRedirect(req.getContextPath() + "/story?uuid=" + story_uuid);
                return;
            }
            System.out.println("User attribute in the session  returns null!!!");
        }
        // User not connected
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
