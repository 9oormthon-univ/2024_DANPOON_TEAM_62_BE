package com.example.MapleArticles.dto.post.response;

import com.example.MapleArticles.domain.post.Post;
import com.example.MapleArticles.domain.user.User;

public class PostResponse {
    private long id;
    private String title;
    private String content;
    private User user;
    private String category;
    private long likes;
    private long views;

    public PostResponse(long id, String title, String content, User user, String category, long likes, long views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
        this.likes = likes;
        this.views = views;
    }

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser();
        this.category = post.getCategory();
        this.likes = post.getLikes();
        this.views = post.getViews();
    }

    public PostResponse(long id, Post post) {
        this.id = id;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser();
        this.category = post.getCategory();
        this.likes = post.getLikes();
        this.views = post.getViews();
    }
}
