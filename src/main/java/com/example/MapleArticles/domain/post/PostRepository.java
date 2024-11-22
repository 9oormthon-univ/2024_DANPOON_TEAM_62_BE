package com.example.MapleArticles.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    // 좋아요 기준 상위 3개
    List<Post> findTop3ByOrderByLikesDesc();
    // 작성일 기준 최신 10개
    List<Post> findTop10ByOrderByCreatedAtDesc();
}
