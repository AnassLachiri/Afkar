package com.afkar.dao;

import com.afkar.models.Story;

public interface StoryDAO {
    void create( Story story ) throws DAOException;

    void update( Story story ) throws DAOException;

    Story find( long id ) throws DAOException;

    Story find( String uuid ) throws DAOException;

    void delete( String uuid ) throws DAOException;
}
