package com.afkar.dao;

import com.afkar.models.Reply;

import java.util.ArrayList;

public interface ReplyDAO {
    void create( Reply reply ) throws DAOException;

    Reply find( long id ) throws DAOException;

    ArrayList<Reply> findReplies(long comment_id) throws DAOException;

    void delete( long reply_id ) throws DAOException;

}
