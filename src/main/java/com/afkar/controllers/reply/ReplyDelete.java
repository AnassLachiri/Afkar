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

public class ReplyDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reply_id = req.getParameter("reply_id");
        String story_uuid = req.getParameter("story_uuid");
        if(reply_id == null || story_uuid == null){
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }


        if(userLoggedIn(req.getSession())){
            User user = (User) req.getSession().getAttribute("user");

            if(user!=null) {

                DAOFactory daoFactory = DAOFactory.getInstance();

                StoryDAO storyDAO = daoFactory.getStoryDao();
                Story story = storyDAO.find(story_uuid);

                ReplyDAO replyDAO = daoFactory.getReplyDao();
                Reply reply = replyDAO.find(Long.valueOf(reply_id));

                if (reply.getUser_id() == user.getId()) {

                    replyDAO.delete(reply.getId());

                    resp.sendRedirect(req.getContextPath() + "/story?uuid=" + story_uuid);
                    return;
                }
            }
        }
        // User logged out
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
