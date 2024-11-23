package com.example.MapleArticles.dto.post.request;

import java.util.List;

public class PostCreateRequest {
    private String title;
    private String content;
    private long userId;
    private String category;
    private List<byte[]> pictures;

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

    public List<byte[]> getPictures() {
        return pictures;
    }

    public void setPictures(List<byte[]> pictures) {
        this.pictures = pictures;
    }

    //test
    @Override
    public String toString() {
        return "PostCreateRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                '}';
    }

}
