package com.example.MapleArticles.dto.post.request;

public class CommentUpdateRequest {
    private long id;
    private String content;
    private long postId;
    private long userId;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }
}
