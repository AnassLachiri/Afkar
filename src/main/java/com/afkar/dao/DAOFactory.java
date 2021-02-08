package com.afkar.dao;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FILE_PROPERTIES      = "/com/afkar/dao/dao.properties";
    private static final String PROPERTY_URL         = "url";
    private static final String PROPERTY_DRIVER      = "driver";
    private static final String PROPERTY_USERNAME    = "user";
    private static final String PROPERTY_PASSWORD    = "password";

    private final String url;
    private final String username;
    private final String password;

    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
//        Properties properties = new Properties();
        String url = "jdbc:mysql://localhost:3306/afkar";
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "password";

//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        InputStream fileProperties = classLoader.getResourceAsStream( FILE_PROPERTIES );
//
//        if ( fileProperties == null ) {
//            throw new DAOConfigurationException( "properties file " + FILE_PROPERTIES + " not found." );
//        }
//
//        try {
//            properties.load( fileProperties );
//            url = properties.getProperty( PROPERTY_URL );
//            driver = properties.getProperty( PROPERTY_DRIVER );
//            username = properties.getProperty( PROPERTY_USERNAME );
//            password = properties.getProperty( PROPERTY_PASSWORD );
//        } catch ( IOException e ) {
//            throw new DAOConfigurationException( "Properties " + FILE_PROPERTIES + " not loaded", e );
//        }

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