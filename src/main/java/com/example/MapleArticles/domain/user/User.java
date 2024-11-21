package com.example.MapleArticles.domain.user;

import com.example.MapleArticles.domain.post.Post;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> userPosts = new ArrayList<>();

    protected User() {}

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
