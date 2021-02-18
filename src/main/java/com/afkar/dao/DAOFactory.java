package com.afkar.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    private final String url;
    private final String username;
    private final String password;

    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        String url = "jdbc:mysql://localhost:3306/afkar?useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "password";

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver not found in the classpath.", e );
        }

        return new DAOFactory( url, username, password );
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    public UserDAO getUserDao() {
        return new UserDAOImpl( this );
    }
    public StoryDAO getStoryDao() {
        return new StoryDAOImpl( this );
    }
    public CommentDAO getCommentDao() {
        return new CommentDAOImpl( this );
    }
    public ReplyDAO getReplyDao() {
        return new ReplyDAOImpl( this );
    }
}