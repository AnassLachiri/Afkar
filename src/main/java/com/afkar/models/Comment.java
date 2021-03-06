package com.afkar.models;

import java.sql.Timestamp;

public class Comment {
    private long id;
    private long story_id;
    private long user_id;
    private String content;
    private Timestamp created_at;

    public Comment(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStory_id() {
        return story_id;
    }

    public void setStory_id(long story_id) {
        this.story_id = story_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
