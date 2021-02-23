package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.afkar.dao.DAOUtils.*;

public class CommentDAOImpl implements CommentDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, story_id, user_id, content, created_at FROM comments WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO comments (story_id, user_id, content, created_at) VALUES (?, ?, ?, NOW())";
    private static final String SQL_SELECT_PAR_STORY_ID = "SELECT id, story_id, user_id, content, created_at FROM comments WHERE story_id = ?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM comments WHERE id = ?";

    public CommentDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Comment comment) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_INSERT, true, comment.getStory_id(), comment.getUser_id(), comment.getContent());
            int status = preparedStatement.executeUpdate();
            if ( status == 0 ) {
                throw new DAOException( "Error while creating the user, No line added to the table." );
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                comment.setId( resultSet.getLong( 1 ) );
            } else {
                throw new DAOException( "Error while creating the user, No id returned." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }
    }

    @Override
    public Comment find(long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Comment comment = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_ID, false, String.valueOf(id) );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                comment = mapComment( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return comment;
    }

    @Override
    public ArrayList<Comment> findComments(long story_id) throws DAOException {
        ArrayList<Comment> comments = new ArrayList<Comment>();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Comment comment = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_STORY_ID, false, String.valueOf(story_id) );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                do {
                    comment = mapComment(resultSet);
                    comments.add(comment);
                }while(resultSet.next());
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return comments;
    }

    @Override
    public void delete(long comment_id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        ReplyDAO replyDAO = daoFactory.getReplyDao();

        for(Reply reply: replyDAO.findReplies(comment_id)){
            replyDAO.delete(reply.getId());
        }

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_DELETE_PAR_ID, true, String.valueOf(comment_id));
            int status = preparedStatement.executeUpdate();
            if ( status == 0 ) {
                throw new DAOException( "Error while creating the user, No line added to the table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( preparedStatement, connexion );
        }
    }


    // this function is for testing
    public long countAll() throws DAOException, SQLException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connexion = daoFactory.getConnection();
        preparedStatement = prepareStatement( connexion, "SELECT count(*) as hy FROM comments", false );
        resultSet = preparedStatement.executeQuery();
        if ( resultSet.next() ) {
            return resultSet.getLong("hy");
        }
        return 0l;
    }

}
