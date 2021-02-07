package com.afkar.dao;

import com.afkar.models.Story;

public interface StoryDAO {
    void create( Story story ) throws DAOException;

    Story find( long id ) throws DAOException;

}
