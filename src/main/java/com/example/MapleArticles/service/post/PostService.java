package com.example.MapleArticles.service.post;

import com.example.MapleArticles.domain.post.Post;
import com.example.MapleArticles.domain.post.PostPicture;
import com.example.MapleArticles.domain.post.PostPictureRepository;
import com.example.MapleArticles.domain.post.PostRepository;
import com.example.MapleArticles.domain.user.User;
import com.example.MapleArticles.domain.user.UserRepository;
import com.example.MapleArticles.dto.post.request.PostCreateRequest;
import com.example.MapleArticles.dto.post.request.PostUpdateRequest;
import com.example.MapleArticles.dto.post.response.PostResponse;
import com.example.MapleArticles.repository.post.PostJdbcRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostPictureRepository postPictureRepository;
    private final UserRepository userRepository;
    private final PostJdbcRepository postJdbcRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, PostPictureRepository postPictureRepository, @Qualifier("postJdbcRepository") PostJdbcRepository postJdbcRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postPictureRepository = postPictureRepository;
        this.postJdbcRepository = postJdbcRepository;
    }


    //게시물 생성
    @Transactional
    public void savePost(PostCreateRequest request) {
        //Post post = postRepository.save(new Post(request.getTitle(), request.getContent(),
        //        request.getUserId(), request.getCategory()));

        //postRepository.save(post);

        Post post = new Post(
                request.getTitle(),
                request.getContent(),
                request.getUserId(),
                request.getCategory()
        );
        System.out.println("Mapped Post object: " + post);
        postRepository.save(post);

        if(request.getPictures() != null) {
            List<PostPicture> pictures = new ArrayList<>();
            for(byte[] pictureData : request.getPictures()) {
                pictures.add(new PostPicture(post, pictureData));
            }

            postPictureRepository.saveAll(pictures);
        }

        if(request.getPictureUrls() != null) {
            List<PostPicture> pictures = new ArrayList<>();
            for(String pictureUrl : request.getPictureUrls()) {
                pictures.add(new PostPicture(post, pictureUrl));
            }

            postPictureRepository.saveAll(pictures);
        }
    }


    //게시물 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

/*
    //게시물 상세 조회
    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostResponse::new)
                .orElseThrow(IllegalArgumentException::new);
    }


 */
    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return postRepository.findWithPicturesById(id)
                .map(PostResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }


    //게시물 수정
    @Transactional
    public void updatePost(PostUpdateRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        post.updateTitle(request.getTitle());
        post.updateContent(request.getContent());
        post.updateCategory(request.getCategory());
        post.updateUpdatedAt();

        postRepository.save(post);

        if(request.getPictures() != null) {
            postPictureRepository.deleteByPostId(post.getId());
            List<PostPicture> pictures = new ArrayList<>();
            for(byte[] pictureData : request.getPictures()) {
                pictures.add(new PostPicture(post, pictureData));
            }
            postPictureRepository.saveAll(pictures);
        }

        if(request.getPicutreUrls() != null) {
            postPictureRepository.deleteByPostId(post.getId());
            List<PostPicture> pictures = new ArrayList<>();
            for(String pictureUrl : request.getPicutreUrls()) {
                pictures.add(new PostPicture(post, pictureUrl));
            }
            postPictureRepository.saveAll(pictures);
        }
    }

    // 좋아요 추가
    public void likePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        post.incrementLikes(); // 좋아요 수 증가
        postRepository.save(post); // 게시물 DB에 좋아요 수 반영
    }


    //게시물 삭제 (게시물 id로)
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        postPictureRepository.deleteByPostId(id);
        postRepository.delete(post);
    }

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
}
