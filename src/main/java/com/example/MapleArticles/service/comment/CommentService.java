package com.example.MapleArticles.service.comment;

import com.example.MapleArticles.domain.comment.Comment;
import com.example.MapleArticles.domain.comment.CommentRepository;
import com.example.MapleArticles.dto.post.request.CommentCreateRequest;
import com.example.MapleArticles.dto.post.request.CommentUpdateRequest;
import com.example.MapleArticles.dto.post.response.CommentResponse;
import com.example.MapleArticles.repository.comment.CommentJdbcRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentJdbcRepository commentJdbcRepository;

    public CommentService(CommentRepository commentRepository, @Qualifier("commentJdbcRepository") CommentJdbcRepository commentJdbcRepository) {
        this.commentRepository = commentRepository;
        this.commentJdbcRepository = commentJdbcRepository;
    }

    @Transactional
    public void saveComment(CommentCreateRequest request) {
        Comment comment = new Comment(request.getContent(), request.getPostId(), request.getUserId());
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateComment(CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        comment.updateContent(request.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        commentRepository.delete(comment);
    }

}
