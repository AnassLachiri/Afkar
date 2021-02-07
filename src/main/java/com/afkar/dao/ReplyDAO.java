package com.afkar.dao;

import com.afkar.models.Reply;

public interface ReplyDAO {
    void create( Reply reply ) throws DAOException;

    Reply find( long id ) throws DAOException;
}
