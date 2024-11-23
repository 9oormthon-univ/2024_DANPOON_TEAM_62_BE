package com.example.MapleArticles.dto.post.response;

import com.example.MapleArticles.domain.post.Post;
import com.example.MapleArticles.domain.post.PostPicture;
import com.example.MapleArticles.domain.user.User;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponse {
    private long id;
    private String title;
    private String content;
    private long userId;
    private String category;
    private long likes;
    private long views;
    private List<byte[]> pictures;
    //private List<String> pictures;
    private List<String> pictureUrls;

    public PostResponse(long id, String title, String content, long userId, String category, long likes, long views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.category = category;
        this.likes = likes;
        this.views = views;
    }

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.category = post.getCategory();
        this.likes = post.getLikes();
        this.views = post.getViews();
        this.pictures = post.getPictures().stream()
                .map(PostPicture::getPicture)
                .collect(Collectors.toList());
        this.pictureUrls = post.getPictures().stream()
                .map(PostPicture::getPictureUrl)
                .filter(url -> url != null && !url.isBlank())
                .collect(Collectors.toList());
/*
        this.pictures = post.getPictures().stream()
                .map(PostPicture::getPicture)
                .map(Base64.getEncoder()::encodeToString) // Base64 인코딩
                .collect(Collectors.toList());


 */

        // 사진 URL 초기화
        this.pictureUrls = post.getPictures().stream()
                .map(PostPicture::getPictureUrl)
                .filter(url -> url != null && !url.isEmpty()) // null 체크
                .collect(Collectors.toList());

        System.out.println("Pictures: " + this.pictures);
        System.out.println("Picture URLs: " + this.pictureUrls);

    }

    public PostResponse(long id, Post post) {
        this.id = id;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.category = post.getCategory();
        this.likes = post.getLikes();
        this.views = post.getViews();
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

    public List<byte[]> getPictures() {
        return pictures;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }
}
