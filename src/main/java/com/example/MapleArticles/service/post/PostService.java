package com.example.MapleArticles.service.post;

import com.example.MapleArticles.domain.post.Post;
import com.example.MapleArticles.domain.post.PostRepository;
import com.example.MapleArticles.dto.post.request.PostCreateRequest;
import com.example.MapleArticles.dto.post.request.PostUpdateRequest;
import com.example.MapleArticles.dto.post.response.PostResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    //게시물 생성
    @Transactional
    public void savePost(PostCreateRequest request) {
        Post post = postRepository.save(new Post(request.getTitle(), request.getContent(),
                request.getUser(), request.getCategory(), request.getLikes(), request.getViews()));
    }


    //게시물 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    //게시물 수정
    @Transactional
    public void updatePost(PostUpdateRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        post.updateTitle(request.getTitle());
        post.updateContent(request.getContent());
        post.updateCategory(request.getCategory());

        postRepository.save(post);
    }


    //게시물 삭제 (게시물 제목으로)
    @Transactional
    public void deletePost(String title) {
        Post post = postRepository.findByTitle(title)
                .orElseThrow(IllegalArgumentException::new);

        postRepository.delete(post);
    }

    // 핫 게시물 조회
    @Transactional(readOnly = true)
    public List<PostResponse> getBestPosts() {
        return postRepository.findTop3ByOrderByLikesDesc().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    // 최근 게시물 조회
    @Transactional(readOnly = true)
    public List<PostResponse> getLatestPosts() {
        return postRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
    // 지역별 게시물 조회
    public List<PostResponse> getPostsByRegion(String region) {
        List<Post> posts = postRepository.findByRegion(region);
        return posts.stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }
}
