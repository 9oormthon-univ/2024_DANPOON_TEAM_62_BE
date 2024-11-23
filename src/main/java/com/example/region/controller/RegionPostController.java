package com.example.region.controller;

import com.example.region.request.PostCreateRequest;
import com.example.region.request.PostUpdateRequest;
import com.example.region.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.region.dto.PostResponse;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionPostController {
    private final PostService postService;

    public RegionPostController(PostService postService) {
        this.postService = postService;
    }

    // 글쓰기
    @PostMapping("/{region}")
    public ResponseEntity<Void> createPost(@PathVariable String region, @RequestBody PostCreateRequest request) {
        postService.createPost(region, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 게시물 수정
    @PutMapping("/{region}/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable String region,
                                           @PathVariable Long postId,
                                           @RequestBody PostUpdateRequest request
                                           ) {
        postService.updatePost(region, postId, request);
        return ResponseEntity.ok().build();
    }

    // 게시물 삭제 (사용자 검증 추가)
    @DeleteMapping("/{region}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String region,
                                           @PathVariable Long postId,
                                           @RequestBody PostUpdateRequest request
                                           ) {
        postService.deletePost(region, postId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 게시물 좋아요
    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postService.likePost(postId);
        return ResponseEntity.ok().build();
    }


    // 특정 지역의 게시물 조회
    @GetMapping("/{region}")
    public ResponseEntity<List<PostResponse>> getPostsByRegion(@PathVariable String region) {
        List<PostResponse> posts = postService.getPostsByRegion(region);
        return ResponseEntity.ok(posts);
    }
}
