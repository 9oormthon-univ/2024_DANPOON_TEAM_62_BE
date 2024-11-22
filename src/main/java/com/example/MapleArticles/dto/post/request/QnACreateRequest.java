package com.example.MapleArticles.dto.post.request;

public class QnACreateRequest {
    private String title;
    private String content;
    //private String comment;
    private long userId;
    private String category;

    public QnACreateRequest(String title, String content, long userId, String category) {
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
