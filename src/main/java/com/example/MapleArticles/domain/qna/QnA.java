package com.example.MapleArticles.domain.qna;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 255, name = "title")
    private String title;

    @Column
    private String content;

    //@Column
    //private String comment;

    private long userId;

    private String category;

    private long likes;
    private long views;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    protected QnA() {}

    public QnA(String title) {
        if(title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title;
    }

    public QnA(String title, String content, long userId, String category) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.category = category;
        this.likes = 0;
        this.views = 0;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    /*
        public String getComment() {
            return comment;
        }
     */
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateCategory(String category) {
        this.category = category;
    }

}
