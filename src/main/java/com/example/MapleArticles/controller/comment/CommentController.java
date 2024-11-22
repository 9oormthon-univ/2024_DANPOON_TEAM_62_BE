package com.example.MapleArticles.controller.comment;

import com.example.MapleArticles.dto.post.request.CommentCreateRequest;
import com.example.MapleArticles.dto.post.request.CommentUpdateRequest;
import com.example.MapleArticles.dto.post.response.CommentResponse;
import com.example.MapleArticles.service.comment.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public void savecomment(@RequestBody CommentCreateRequest request) {
        commentService.saveComment(request);
    }

    @GetMapping("/comment")
    public List<CommentResponse> getComments() {
        return commentService.getComments();
    }

    @PutMapping("/comment")
    public void updateComment(@RequestBody CommentUpdateRequest request) {
        commentService.updateComment(request);
    }

    @DeleteMapping("/comment")
    public void deleteComment(@RequestParam Long id) {
        commentService.deleteComment(id);
    }
}
