package com.example.MapleArticles.domain.comment;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private String content;

    private long postId;

    private long userId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    protected Comment() {}

    public Comment(String content) {
        if(content == null || content.isBlank())
            throw new IllegalArgumentException();

        this.content = content;
    }

    public Comment(String content, long postId, long userId) {
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = createdAt;
    }

    public Long getId() {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = new Date(System.currentTimeMillis());
    }
}
