package com.example.MapleArticles.dto.post.request;

import java.util.List;

public class PostUpdateRequest {
    private long id;
    private String title;
    private String content;
    private String category;
    private List<byte[]> pictures;
    private List<String> picutreUrls;

    public long getId() {
        return id;
    }

    public String getTitle() { return title; }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public List<byte[]> getPictures() {
        return pictures;
    }

    public List<String> getPicutreUrls() {
        return picutreUrls;
    }
}
