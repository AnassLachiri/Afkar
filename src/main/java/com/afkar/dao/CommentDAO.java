package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;

public interface CommentDAO {
    void create( Comment comment ) throws DAOException;

    Comment find( long id ) throws DAOException;
}
