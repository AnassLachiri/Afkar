package com.afkar.controllers.story;

import com.afkar.dao.CommentDAO;
import com.afkar.dao.DAOFactory;
import com.afkar.dao.ReplyDAO;
import com.afkar.dao.StoryDAO;
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

            story = storyDAO.find(uuid);
            if(story == null){
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }

            ArrayList<Comment> comments = new ArrayList<Comment>();
            comments = commentDAO.findComments(story.getId());
            HashMap<Long, ArrayList<Reply>> replies = new HashMap<Long, ArrayList<Reply>>();
            for(Comment comment: comments){
                replies.put(comment.getId(), replyDAO.findReplies(comment.getId()));

            }
            System.out.println(comments.size());
            System.out.println(replies.size());

            User user = (User) req.getSession().getAttribute("user");

            req.setAttribute("title", story.getTitle());
            req.setAttribute("subtitle", story.getSubtitle());
            req.setAttribute("content", story.getContent());
            req.setAttribute("uuid", story.getUuid());
            req.setAttribute("user_id", story.getUser_id());
            req.setAttribute("login_id", user.getId());

            req.setAttribute("comments", comments);
            req.setAttribute("replies", replies);
            this.getServletContext().getRequestDispatcher("/story.jsp").forward(req, resp);
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
