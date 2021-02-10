package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;
import com.afkar.models.Story;
import com.afkar.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.afkar.dao.DAOUtils.*;

public class StoryDAOImpl implements StoryDAO{
    private final DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_ID = "SELECT id, uuid, user_id, title, subtitle, content, total_likes, keywords, created_at FROM stories WHERE id = ?";
    private static final String SQL_SELECT_PAR_UUID = "SELECT id, uuid, user_id, title, subtitle, content, total_likes, keywords, created_at FROM stories WHERE uuid = ?";
    private static final String SQL_INSERT = "INSERT INTO stories (uuid, user_id, title, subtitle, content, total_likes, keywords, created_at) VALUES (?, ?, ?, ?, ?, 0, ?, NOW())";
    private static final String SQL_UPDATE = "UPDATE stories SET title = ?, subtitle = ?, content = ?, keywords = ? WHERE uuid = ?";
    private static final String SQL_DELETE_PAR_UUID = "DELETE FROM stories WHERE uuid = ?";
    private static final String SQL_SELECT_ALL_STORIES_BY_ID = "SELECT * FROM stories ORDER BY created_at DESC LIMIT ? OFFSET ? ";
    private static final String SQL_SELECT_PROFILE_STORIES_BY_ID = "SELECT * FROM stories WHERE user_id = ? ORDER BY created_at DESC LIMIT ? OFFSET ? ";

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
            preparedStatement = prepareStatement( connexion, SQL_INSERT, true, story.getUuid(), story.getUser_id(), story.getTitle(), story.getSubtitle() , story.getContent(), story.getKeywords());
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
    public void update(Story story) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_UPDATE, true, story.getTitle(), story.getSubtitle() , story.getContent(), story.getKeywords(), story.getUuid());
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


    @Override
    public Story find(String uuid) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Story story = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_PAR_UUID, false, String.valueOf(uuid) );
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

    @Override
    public void delete(String uuid) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        CommentDAO commentDAO = daoFactory.getCommentDao();
        StoryDAO storyDAO = daoFactory.getStoryDao();

        Story story = storyDAO.find(uuid);

        for(Comment comment: commentDAO.findComments(story.getId())){
            commentDAO.delete(comment.getId());
        }

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_DELETE_PAR_UUID, true, uuid);
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

    @Override
    public ArrayList<Story> findAllStories(long page_count) throws DAOException {
        if(page_count <= 1) page_count = 1;
        long limit = 10;
        long offset = limit * ( page_count -1 );

        ArrayList<Story> stories = new ArrayList<Story>();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Story story = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = prepareStatement( connexion, SQL_SELECT_ALL_STORIES_BY_ID, false, limit, offset );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                do {
                    story = mapStory(resultSet);
                    stories.add(story);
                }while(resultSet.next());
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            close( resultSet, preparedStatement, connexion );
        }

        return stories;

    }

    @Override
    public ArrayList<Story> findProfileStories(String username, long page_count) throws DAOException {
        if(page_count <= 1) page_count = 1;
        long limit = 10;
        long offset = limit * ( page_count -1 );

        ArrayList<Story> stories = new ArrayList<Story>();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Story story = null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();
        User user = userDAO.find(username);
        if(user != null) {
            try {
                connexion = daoFactory.getConnection();
                preparedStatement = prepareStatement(connexion, SQL_SELECT_PROFILE_STORIES_BY_ID, false, user.getId(), limit, offset);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    do {
                        story = mapStory(resultSet);
                        stories.add(story);
                    } while (resultSet.next());
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                close(resultSet, preparedStatement, connexion);
            }
        }
        return stories;
    }
}
