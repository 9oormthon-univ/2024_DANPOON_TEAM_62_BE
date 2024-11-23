package com.example.MapleArticles.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostPictureRepository extends JpaRepository<PostPicture, Long> {
    void deleteByPostId(Long postId);
}
