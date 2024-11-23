package com.example.MapleArticles.dto.post.response;

import com.example.MapleArticles.domain.post.Post;
import com.example.MapleArticles.domain.post.PostPicture;
import com.example.MapleArticles.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {
    private long id;
    private String title;
    private String content;
    private long userId;
    private String category;
    private long likes;
    private long views;
    private List<PostPicture> pictures = new ArrayList<>();

    public PostResponse(long id, String title, String content, long userId, String category, long likes, long views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.category = category;
        this.likes = likes;
        this.views = views;
    }

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.category = post.getCategory();
        this.likes = post.getLikes();
        this.views = post.getViews();
        this.pictures = post.getPictures();
    }

    public PostResponse(long id, Post post) {
        this.id = id;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.category = post.getCategory();
        this.likes = post.getLikes();
        this.views = post.getViews();
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
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
