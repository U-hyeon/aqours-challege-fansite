package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPostNotDeleted() {
        return postRepository.getAllPostNotDeleted();
    }

    public Post savePost(Post post) {
        if (isDoubledRequest(post)) {
            throw new IllegalStateException("동일한 게시물을 작성할 수 없습니다.");
        }
        return postRepository.save(post);
    }

    public int deletePost(String postId) {
        return postRepository.deletePost(Long.parseLong(postId));
    }

    public boolean isDoubledRequest(Post post) {
        List<Post> samePostFromRecent10Posts = postRepository.findByMemberIdAndTitleWhereRowNumIn10(post.getMemberId(), post.getTitle());
        if (samePostFromRecent10Posts == null || samePostFromRecent10Posts.isEmpty()) {
            return false;
        }
        return true;
    }

    public Post getPostByPostId(Long postId) {
        return postRepository.findByPostId(postId);
    }

}
