package com.afkar.dao;

import com.afkar.models.Reply;
import com.afkar.models.Story;
import com.afkar.models.User;

import java.util.ArrayList;

public interface StoryDAO {

    void create( Story story ) throws DAOException;

    void update( Story story ) throws DAOException;

    Story find( long id ) throws DAOException;

    Story find( String uuid ) throws DAOException;

    void delete( String uuid ) throws DAOException;

    void saveStory( User user, String uuid ) throws DAOException;

    void likeStory( User user, String uuid ) throws DAOException;

    ArrayList<Story> findAllStories(long page_count) throws DAOException;

    ArrayList<Story> findProfileStories(String username, long page_count) throws DAOException;

    ArrayList<Story> findSavedStories(User user, long page_count) throws DAOException;

}
