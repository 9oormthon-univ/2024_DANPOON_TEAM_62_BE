package com.example.MapleArticles.repository.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QnAJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QnAJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
