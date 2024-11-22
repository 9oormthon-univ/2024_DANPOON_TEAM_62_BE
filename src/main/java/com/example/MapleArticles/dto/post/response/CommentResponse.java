package com.example.MapleArticles.dto.post.response;

import com.example.MapleArticles.domain.comment.Comment;

public class CommentResponse {
    private long id;
    private String content;
    private long postId;
    private long userId;


    public CommentResponse(long id, String content, long postId, long userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.postId = comment.getPostId();
    }

    public long getId() {
        return id;
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
