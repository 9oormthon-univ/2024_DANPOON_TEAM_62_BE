package com.example.MapleArticles.domain.post;

import com.example.MapleArticles.domain.user.User;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 255, name = "title")
    private String title;

    @Column(nullable = false)
    private String content;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    private String category;

    private long likes;
    private long views;

    private long createdAt;
    private long updatedAt;


    protected Post() {}

    public Post(String title) {
        if(title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title;
    }

    public Post(String title, String content, User user, String category, long likes, long views) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
        this.likes = likes;
        this.views = views;
        this.createdAt = System.currentTimeMillis();
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

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void updateTitle(String title) {
        this.title = title;
        this.updatedAt = System.currentTimeMillis();
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = System.currentTimeMillis();
    }

    public void updateCategory(String category) {
        this.category = category;
    }
}
