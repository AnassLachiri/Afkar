package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;
import com.afkar.models.Story;
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

    public static User mapUser(ResultSet resultSet ) throws SQLException {
        User user = new User(resultSet.getLong( "id" ));
        user.setEmail( resultSet.getString( "email" ) );
        user.setPassword( resultSet.getString( "password" ) );
        user.setUsername( resultSet.getString( "username" ) );
        user.setCreated_at( resultSet.getTimestamp( "created_at" ) );
        return user;
    }

    public static Story mapStory(ResultSet resultSet ) throws SQLException {
        Story story = new Story(resultSet.getLong( "id" ));
        story.setUser_id( resultSet.getLong( "user_id" ) );
        story.setTitle( resultSet.getString( "title" ) );
        story.setSubtitle( resultSet.getString( "subtitle" ) );
        story.setContent( resultSet.getString( "content" ) );
        story.setTotal_likes( resultSet.getLong( "content" ) );
        story.setKeywords( resultSet.getString( "content" ) );
        story.setCreated_at( resultSet.getTimestamp( "created_at" ) );
        return story;
    }

    public static Comment mapComment(ResultSet resultSet ) throws SQLException {
        Comment comment = new Comment(resultSet.getLong( "id" ));
        comment.setStory_id( resultSet.getLong( "story_id" ) );
        comment.setUser_id( resultSet.getLong( "user_id" ) );
        comment.setContent( resultSet.getString( "content" ) );
        comment.setTotal_likes( resultSet.getLong( "total_likes" ) );
        comment.setCreated_at( resultSet.getTimestamp( "created_at" ) );
        return comment;
    }

    public static Reply mapReply(ResultSet resultSet ) throws SQLException {
        Reply reply = new Reply(resultSet.getLong( "id" ));
        reply.setComment_id( resultSet.getLong( "comment_id" ) );
        reply.setUser_id( resultSet.getLong( "user_id" ) );
        reply.setContent( resultSet.getString( "content" ) );
        reply.setTotal_likes( resultSet.getLong( "total_likes" ) );
        reply.setCreated_at( resultSet.getTimestamp( "created_at" ) );
        return reply;
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
