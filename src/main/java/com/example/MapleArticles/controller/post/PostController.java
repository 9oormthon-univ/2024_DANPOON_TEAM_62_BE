package com.example.MapleArticles.controller.post;

import com.example.MapleArticles.dto.post.request.PostCreateRequest;
import com.example.MapleArticles.dto.post.request.PostUpdateRequest;
import com.example.MapleArticles.dto.post.response.PostResponse;
import com.example.MapleArticles.service.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        postService.savePost(request);
    }

    @GetMapping("/post")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("/post")
    public void updatePost(@RequestBody PostUpdateRequest request) {
        postService.updatePost(request);
    }

    @DeleteMapping("/post")

    public void deletePost(@RequestParam String title) {
        postService.deletePost(title);
    }
    @GetMapping("/post/best")
    public List<PostResponse> getBestPosts() {
        return postService.getBestPosts();
    }
    @GetMapping("/post/latest")
    public List<PostResponse> getLatestPosts() {
        return postService.getLatestPosts();
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<PostResponse>> getPostsByRegion(@PathVariable String region) {
        List<PostResponse> posts = postService.getPostsByRegion(region);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}

