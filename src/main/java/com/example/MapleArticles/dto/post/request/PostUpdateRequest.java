package com.example.MapleArticles.dto.post.request;

public class PostUpdateRequest {
    private long id;
    private String title;
    private String content;
    private String category;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }
}
