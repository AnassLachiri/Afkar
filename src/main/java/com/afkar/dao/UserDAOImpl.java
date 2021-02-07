package com.afkar.dao;

import com.afkar.models.User;
import java.sql.*;
import static com.afkar.dao.DAOUtils.close;
import static com.afkar.dao.DAOUtils.prepareStatement;
import static com.afkar.dao.DAOUtils.mapUser;


public class UserDAOImpl implements UserDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, email, username, password, created_at FROM users WHERE id = ?";
    private static final String SQL_SELECT_PAR_USERNAME = "SELECT id, email, username, password, created_at FROM users WHERE username = ?";
    private static final String SQL_INSERT = "INSERT INTO users (email, password, username, created_at) VALUES (?, ?, ?, NOW())";

    public UserDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(User user) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_INSERT, true, user.getEmail(), user.getPassword(), user.getUsername() );
            int status = preparedStatement.executeUpdate();
            if ( status == 0 ) {
                throw new DAOException( "Error while creating the user, No line added to the table." );
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                user.setId( resultSet.getLong( 1 ) );
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
    public User find(long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_ID, false, String.valueOf(id) );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                user = mapUser( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return user;
    }

    @Override
    public User find(String username) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_USERNAME, false, username );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                user = mapUser( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return user;
    }

}
