package com.example.MapleArticles.dto.post.request;

public class CommentCreateRequest {
    private String content;
    private long userId;
    private long postId;

    public CommentCreateRequest(String content, long userId, long postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }
}
