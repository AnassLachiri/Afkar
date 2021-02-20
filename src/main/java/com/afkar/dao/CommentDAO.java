package com.afkar.dao;

import com.afkar.models.Comment;
import com.afkar.models.Reply;
import com.afkar.models.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface CommentDAO {
    void create( Comment comment ) throws DAOException;

    Comment find( long id ) throws DAOException;

    ArrayList<Comment> findComments(long story_id) throws DAOException;

    void delete( long comment_id ) throws DAOException;

}
