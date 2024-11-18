package com.example.MapleArticles.dto.post.request;

import com.example.MapleArticles.domain.user.User;

public class PostCreateRequest {
    private String title;
    private String content;
    private User user;
    private String category;
    private long likes;
    private long views;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public String getCategory() {
        return category;
    }

    public long getLikes() {
        return likes;
    }

    public long getViews() {
        return views;
    }
}
