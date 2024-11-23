package com.example.MapleArticles.domain.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnARepository extends JpaRepository<QnA, Long> {
    //Optional<QnA> findById(long id);
    Optional<QnA> findByTitle(String title);
}
