package com.example.MapleArticles.controller.post;

import com.example.MapleArticles.dto.post.request.PostCreateRequest;
import com.example.MapleArticles.dto.post.request.PostUpdateRequest;
import com.example.MapleArticles.dto.post.response.PostResponse;
import com.example.MapleArticles.service.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public void savePost(@RequestBody PostCreateRequest request) {
        //test
        System.out.println("Request received: " + request);

        postService.savePost(request);
    }

    @PostMapping("/post/{postId}/like")
    public void likePost(@PathVariable Long postId) {
        postService.likePost(postId);
    }

    @GetMapping("/post")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/post")
    public void updatePost(@RequestBody PostUpdateRequest request) {
        postService.updatePost(request);
    }

    @DeleteMapping("/post")
    public void deletePost(@RequestParam Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/post/best")
    public List<PostResponse> getBestPosts() {
        return postService.getBestPosts();
    }
    @GetMapping("/post/latest")
    public List<PostResponse> getLatestPosts() {
        return postService.getLatestPosts();
    }
}
