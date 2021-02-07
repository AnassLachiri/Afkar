package com.afkar.dao;

import com.afkar.models.Story;
import com.afkar.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.afkar.dao.DAOUtils.*;

public class StoryDAOImpl implements StoryDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, user_id, title, subtitle, content, total_likes, keywords, created_at FROM stories WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO stories (user_id, title, subtitle, content, total_likes, keywords, created_at) VALUES (?, ?, ?, ?, 0, ?, NOW())";

    public StoryDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Story story) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_INSERT, true, story.getUser_id(), story.getTitle(), story.getSubtitle() , story.getContent(), story.getKeywords());
            int status = preparedStatement.executeUpdate();
            if ( status == 0 ) {
                throw new DAOException( "Error while creating the user, No line added to the table." );
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                story.setId( resultSet.getLong( 1 ) );
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
    public Story find(long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Story story = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_ID, false, String.valueOf(id) );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                story = mapStory( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return story;
    }
}
