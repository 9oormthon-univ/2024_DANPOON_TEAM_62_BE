package com.example.region.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByRegion(String region);  // 지역별 게시물 조회

    // 수정된 메소드
    Optional<Post> findByPostIdAndRegion(Long postId, String region);  // postId와 region으로 찾기
}
