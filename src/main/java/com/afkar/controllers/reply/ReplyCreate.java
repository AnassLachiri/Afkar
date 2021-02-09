package com.afkar.controllers.reply;

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

import static com.afkar.middlewares.Auth.userLoggedIn;

public class ReplyCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");
        String comment_id = req.getParameter("comment_id");
        String story_uuid = req.getParameter("story_uuid");

        if(story_uuid == null || content == null || comment_id == null){
            resp.sendRedirect(req.getContextPath() + "/story?uuid=" + story_uuid);
            return;
        }

        if(userLoggedIn(req.getSession())){
            // User connected

            User user = (User) req.getSession().getAttribute("user");

            if(user!=null){

                Reply reply = new Reply(0);
                reply.setContent(content);
                reply.setComment_id(Long.valueOf(comment_id));
                reply.setUser_id(user.getId());

                DAOFactory daoFactory = DAOFactory.getInstance();
                ReplyDAO replyDAO = daoFactory.getReplyDao();
                replyDAO.create(reply);

                resp.sendRedirect(req.getContextPath() + "/story?uuid=" + story_uuid);
                return;
            }

            System.out.println("User attribute in the session  returns null!!!");
        }
        // User not connected
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
