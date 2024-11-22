package com.example.region.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 게시물 ID

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int likes;


    public Post(String title, String content, Long userId, String region) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.region = region;
        this.likes = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // 좋아요 증가 메서드
    public void incrementLikes() {
        this.likes++;
    }

    // 좋아요 감소 메서드
    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }
    public Long getUserId() {
        return userId;
    }

}
