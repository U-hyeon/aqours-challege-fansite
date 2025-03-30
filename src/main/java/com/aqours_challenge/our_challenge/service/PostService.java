package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.entity.Tag;
import com.aqours_challenge.our_challenge.repository.PostRepository;
import com.aqours_challenge.our_challenge.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    private PostRepository postRepository;
    private TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public List<Post> getAllPostNotDeleted() {
        return postRepository.getAllPostNotDeleted();
    }

    public Post savePost(Post post, List<String> tags) {
        if (isDoubledRequest(post)) {
            throw new IllegalStateException("동일한 게시물을 작성할 수 없습니다.");
        }
        try {
            for (String tagName : tags) {
                if (!tagName.isEmpty() && tagRepository.findByTagName(tagName) == null) {
                    Tag tag = new Tag();
                    tag.setTagName(tagName);
                    tagRepository.save(tag);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("태그 설정 오류");
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
