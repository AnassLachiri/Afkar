package com.afkar.dao;

import com.afkar.models.Reply;
import com.afkar.models.Story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.afkar.dao.DAOUtils.*;

public class ReplyDAOImpl implements ReplyDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, comment_id, user_id, content, total_likes, created_at FROM replies WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO replies (comment_id, user_id, content, total_likes, created_at) VALUES (?, ?, ?, 0, NOW())";

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
}
