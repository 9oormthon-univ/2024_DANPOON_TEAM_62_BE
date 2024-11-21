package com.example.MapleArticles.dto.post.request;

public class PostCreateRequest {
    private String title;
    private String content;
    private long userId;
    private String category;

    public PostCreateRequest(String title, String content, long userId, String category) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.category = category;
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
}
