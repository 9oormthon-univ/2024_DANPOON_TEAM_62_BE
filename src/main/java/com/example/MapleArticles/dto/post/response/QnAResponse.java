package com.example.MapleArticles.dto.post.response;

import com.example.MapleArticles.domain.qna.QnA;

public class QnAResponse {
    private long id;
    private String title;
    private String content;
    private long userId;
    private String category;
    private long likes;
    private long views;

    public QnAResponse(long id, String title, String content, long userId, String category, long likes, long views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.category = category;
        this.likes = likes;
        this.views = views;
    }

    public QnAResponse(QnA qna) {
        this.id = qna.getId();
        this.title = qna.getTitle();
        this.content = qna.getContent();
        this.userId = qna.getUserId();
        this.category = qna.getCategory();
        this.likes = qna.getLikes();
        this.views = qna.getViews();
    }

    public QnAResponse(long id, QnA qna) {
        this.id = id;
        this.title = qna.getTitle();
        this.content = qna.getContent();
        this.userId = qna.getUserId();
        this.category = qna.getCategory();
        this.likes = qna.getLikes();
        this.views = qna.getViews();
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
