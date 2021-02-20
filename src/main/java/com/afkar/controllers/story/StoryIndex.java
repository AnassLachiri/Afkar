package com.afkar.controllers.story;

import com.afkar.dao.*;
import com.afkar.models.Comment;
import com.afkar.models.Reply;
import com.afkar.models.Story;
import com.afkar.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.afkar.middlewares.Auth.userLoggedIn;

public class StoryIndex extends HttpServlet {
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
            Story story = null;
            DAOFactory daoFactory = DAOFactory.getInstance();
            CommentDAO commentDAO = daoFactory.getCommentDao();
            ReplyDAO replyDAO = daoFactory.getReplyDao();
            StoryDAO storyDAO = daoFactory.getStoryDao();
            UserDAO userDAO = daoFactory.getUserDao();

            story = storyDAO.find(uuid);
            if(story == null){
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }

            ArrayList<Comment> comments = new ArrayList<Comment>();
            comments = commentDAO.findComments(story.getId());
            HashMap<Long, ArrayList<Reply>> replies = new HashMap<Long, ArrayList<Reply>>();

            HashMap<Long, User> commentUsers = new HashMap<Long, User>();
            HashMap<Long, User> replyUsers = new HashMap<Long, User>();
            User tempUser;

            ArrayList<Reply> tempReplies;
            for(Comment comment: comments){
                tempReplies = replyDAO.findReplies(comment.getId());
                replies.put(comment.getId(), tempReplies);
                tempUser = userDAO.find(comment.getUser_id());
                commentUsers.put(comment.getId(), tempUser);
                for(Reply reply: tempReplies){
                    tempUser = userDAO.find(reply.getUser_id());
                    replyUsers.put(reply.getId(), tempUser);
                }
            }




            User author = userDAO.find(story.getUser_id());

            if(author != null) {


                req.setAttribute("story", story);
                req.setAttribute("author", author);


                req.setAttribute("comments", comments);
                req.setAttribute("replies", replies);

                req.setAttribute("commentUsers", commentUsers);
                req.setAttribute("replyUsers", replyUsers);
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/story.jsp").forward(req, resp);
                return;
            }
        }
        // User logged out
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
