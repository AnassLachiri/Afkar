package com.afkar.dao;

import com.afkar.models.User;

public interface UserDAO {
    void create( User user ) throws DAOException;

    void follow(User user, String username) throws DAOException;

    User find( long id ) throws DAOException;

    User find( String username ) throws DAOException;
}
