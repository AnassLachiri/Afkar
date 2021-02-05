package com.afkar.dao;

import com.afkar.models.User;

import java.sql.*;

public class DAOUtils {

    public static PreparedStatement prepareStatement(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }

    public static User map(ResultSet resultSet ) throws SQLException {
        User user = new User(resultSet.getLong( "id" ));
        user.setEmail( resultSet.getString( "email" ) );
        user.setPassword( resultSet.getString( "password" ) );
        user.setUsername( resultSet.getString( "username" ) );
        user.setCreated_at( resultSet.getTimestamp( "created_at" ) );
        return user;
    }


    public static void close( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Error while closing ResultSet : " + e.getMessage() );
            }
        }
    }

    public static void close( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Error while closing Statement : " + e.getMessage() );
            }
        }
    }

    public static void close( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Error while closing connexion : " + e.getMessage() );
            }
        }
    }

    public static void close( Statement statement, Connection connexion ) {
        close( statement );
        close( connexion );
    }

    public static void close( ResultSet resultSet, Statement statement, Connection connexion ) {
        close( resultSet );
        close( statement );
        close( connexion );
    }
}
