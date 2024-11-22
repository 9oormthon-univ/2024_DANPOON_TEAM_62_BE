package region.service;

import org.springframework.stereotype.Service;
import region.domain.Post;
import region.domain.PostRepository;
import region.dto.PostResponse;
import region.request.PostCreateRequest;
import region.request.PostUpdateRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시물 저장
    public void createPost(String region, PostCreateRequest request) {
        Post post = new Post(request.getTitle(), request.getContent(), request.getUserId(), region);
        postRepository.save(post); // DB에 저장
    }

    // 좋아요 추가
    public void likePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        post.incrementLikes(); // 좋아요 수 증가
        postRepository.save(post); // 게시물 DB에 좋아요 수 반영
    }

    // 조회수 증가
    public void viewPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        post.incrementViews(); // 조회수 증가
        postRepository.save(post); // 게시물 DB에 조회수 반영
    }

    // 게시물 수정
    public void updatePost(String region, Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        // 권한 확인
        if (!post.getUserId().equals(request.getUserId())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }


        // 권한이 있으면 수정
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        postRepository.save(post);
    }

    // 게시물 삭제
    public void deletePost(String region, Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        // 권한 확인
        if (!post.getUserId().equals(request.getUserId())) {
            throw new SecurityException("이 게시물은 삭제할 권한이 없습니다.");
        }

        // 권한이 있으면 삭제
        postRepository.delete(post);
    }

    // 특정 지역의 게시물 조회
    public List<PostResponse> getPostsByRegion(String region) {
        List<Post> posts = postRepository.findByRegion(region); // DB에서 특정 지역의 게시물 조회
        return posts.stream()
                .map(post -> new PostResponse(post.getPostId(), post.getTitle(), post.getContent(), post.getUserId(), post.getRegion(), post.getCreatedAt(), post.getUpdatedAt(), post.getLikes(), post.getViews()))
                .collect(Collectors.toList()); // PostResponse로 변환하여 반환
    }
}