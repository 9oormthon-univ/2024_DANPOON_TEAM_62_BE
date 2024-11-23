package com.example.MapleArticles.dto.post.request;

public class CommentCreateRequest {
    private String content;
    private long postId;
    private long userId;


    public CommentCreateRequest(String content, long postId, long userId) {
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
