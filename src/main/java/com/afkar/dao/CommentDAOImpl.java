package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.afkar.dao.DAOUtils.*;

public class CommentDAOImpl implements CommentDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, comment_id, user_id, content, total_likes, created_at FROM comments WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO comments (story_id, user_id, content, total_likes, created_at) VALUES (?, ?, ?, 0, NOW())";

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
}
