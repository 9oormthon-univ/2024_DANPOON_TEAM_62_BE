package com.example.MapleArticles.domain.post;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PostPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Lob
    @Column(nullable = false)
    private byte[] picture;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    protected PostPicture() {}

    public PostPicture(Post post, byte[] picture) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (picture == null || picture.length == 0) {
            throw new IllegalArgumentException("Picture cannot be null or empty");
        }
        this.post = post;
        this.picture = picture;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public byte[] getPicture() {
        return picture;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
}