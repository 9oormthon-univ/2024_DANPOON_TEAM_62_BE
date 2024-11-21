package region.dto;

import java.time.LocalDateTime;

public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long userId;
    private String region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likes;  // 좋아요 수
    private int views;  // 조회수

    // 생성자
    public PostResponse(Long postId, String title, String content, Long userId, String region, LocalDateTime createdAt, LocalDateTime updatedAt, int likes, int views) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.region = region;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.views = views;
    }

    // Getter, Setter
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
