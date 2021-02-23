package com.afkar.dao;

import com.afkar.models.Comment;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentDAOImplTest {

    @Test
    void create() throws NoSuchFieldException, SQLException {
        DAOFactory daoFactory = new DAOFactory("jdbc:mysql://localhost:3306/afkar?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "YASSIR_yassir8");

        CommentDAOImpl commentDAO = new CommentDAOImpl(daoFactory);
        Connection connection = mock(Connection.class);

        Comment comment = mock(Comment.class);
        when(comment.getContent()).thenReturn("comCon");
        when(comment.getUser_id()).thenReturn(Long.valueOf(2));
        when(comment.getStory_id()).thenReturn(Long.valueOf(1));


        long a = commentDAO.countAll();
        commentDAO.create(comment);
        Assert.assertEquals(commentDAO.countAll(), a+1);

    }


    @Test
    public void find() throws DAOException, SQLException {
        DAOFactory daoFactory = new DAOFactory("jdbc:mysql://localhost:3306/afkar?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "YASSIR_yassir8");

        CommentDAOImpl commentDAO = new CommentDAOImpl(daoFactory);
        Connection connection = mock(Connection.class);
        Comment comment = mock(Comment.class);
        when(comment.getContent()).thenReturn("testing find()");
        when(comment.getUser_id()).thenReturn(Long.valueOf(2));
        when(comment.getStory_id()).thenReturn(Long.valueOf(1));
        commentDAO.create(comment);
        long a = commentDAO.countAll();

        //don't forget to increment id:39
        Comment comment1 = commentDAO.find(38);

        Assert.assertEquals(comment1.getContent(), "testing find()");
        Assert.assertEquals(comment1.getStory_id(), 1);
        Assert.assertEquals(comment1.getUser_id(), 2);
        Assert.assertEquals(a, commentDAO.countAll());

    }


    @Test
    public void findComments() throws DAOException, SQLException {
        DAOFactory daoFactory = new DAOFactory("jdbc:mysql://localhost:3306/afkar?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "YASSIR_yassir8");

        CommentDAOImpl commentDAO = new CommentDAOImpl(daoFactory);
        Connection connection = mock(Connection.class);

        ArrayList<Comment> comments = commentDAO.findComments(1);


        Assert.assertEquals(comments.size(), commentDAO.countAll()); //because all the comments has the same story_id
        Assert.assertEquals(comments.get(comments.size()-1).getStory_id(), 1);

    }

    @Test
    public void delete() throws DAOException, SQLException {
        DAOFactory daoFactory = new DAOFactory("jdbc:mysql://localhost:3306/afkar?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "YASSIR_yassir8");

        CommentDAOImpl commentDAO = new CommentDAOImpl(daoFactory);
        Connection connection = mock(Connection.class);

        long a = commentDAO.countAll();
        //don't forget de decrement comment_id: 40
        commentDAO.delete(41);
        Assert.assertEquals(commentDAO.countAll(), a-1);
        Assert.assertEquals(commentDAO.find(39), null);




    }
}