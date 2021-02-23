package com.afkar.dao;

import com.afkar.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DAOUtilsTest {


    @Test
    void mapUser() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(Long.valueOf(123));
        when(resultSet.getString("image")).thenReturn("imaagee");
        when(resultSet.getString("email")).thenReturn("emaiill");
        when(resultSet.getString("password")).thenReturn("paasswoord");
        when(resultSet.getString("username")).thenReturn("useernamme");
        Timestamp timestamp = Timestamp.from(Instant.now());
        when(resultSet.getTimestamp("created_at")).thenReturn(timestamp);

        User user = DAOUtils.mapUser(resultSet);

        Assert.assertEquals(user.getId(), 123);
        Assert.assertEquals(user.getUsername(), "useernamme");
        Assert.assertEquals(user.getEmail(), "emaiill");
        Assert.assertEquals(user.getImage(), "imaagee");
        Assert.assertEquals(user.getCreated_at(), timestamp);
        Assert.assertEquals(user.getPassword(), "paasswoord");


    }

    @Test
    void mapStory() {
    }

    @Test
    void mapComment() {
    }

    @Test
    void mapReply() {
    }
}