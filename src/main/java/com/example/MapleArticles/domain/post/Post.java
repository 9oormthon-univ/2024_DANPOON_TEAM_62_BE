package com.example.MapleArticles.domain.post;

import com.example.MapleArticles.domain.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 255, name = "title")
    private String title;

    @Column(nullable = false)
    private String content;

    /*
    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;
    */
    private long userId;

    private String category;

    private long likes;
    private long views;

    private Date createdAt;
    private Date updatedAt;


    protected Post() {}

    public Post(String title) {
        if(title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title;
    }

    public Post(String title, String content, long userId, String category) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.category = category;
        this.likes = 0;
        this.views = 0;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
/*
    public User getUser() {
        return user;
    }

 */

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void updateTitle(String title) {
        this.title = title;
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public void updateCategory(String category) {
        this.category = category;
    }
}
