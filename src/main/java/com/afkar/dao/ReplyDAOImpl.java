package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;
import com.afkar.models.Story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.afkar.dao.DAOUtils.*;

public class ReplyDAOImpl implements ReplyDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, comment_id, user_id, content, created_at FROM replies WHERE id = ?";
    private static final String SQL_SELECT_PAR_COMMENT_ID = "SELECT id, comment_id, user_id, content, created_at FROM replies WHERE comment_id = ?";
    private static final String SQL_INSERT = "INSERT INTO replies (comment_id, user_id, content, created_at) VALUES (?, ?, ?, NOW())";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM replies WHERE id = ?";


    public ReplyDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Reply reply) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_INSERT, true, reply.getComment_id(), reply.getUser_id(), reply.getContent());
            int status = preparedStatement.executeUpdate();
            if ( status == 0 ) {
                throw new DAOException( "Error while creating the user, No line added to the table." );
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                reply.setId( resultSet.getLong( 1 ) );
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
    public Reply find(long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reply reply = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_ID, false, String.valueOf(id) );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                reply = mapReply( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return reply;
    }

    @Override
    public ArrayList<Reply> findReplies(long comment_id) throws DAOException {
        ArrayList<Reply> replies = new ArrayList<Reply>();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reply reply = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_COMMENT_ID, false, String.valueOf(comment_id) );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                do {
                    reply = mapReply(resultSet);
                    replies.add(reply);
                }while(resultSet.next());
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return replies;
    }

    @Override
    public void delete(long reply_id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_DELETE_PAR_ID, true, String.valueOf(reply_id));
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
}
