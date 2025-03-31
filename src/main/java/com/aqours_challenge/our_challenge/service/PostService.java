package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.entity.Tag;
import com.aqours_challenge.our_challenge.entity.TagPost;
import com.aqours_challenge.our_challenge.repository.PostRepository;
import com.aqours_challenge.our_challenge.repository.TagPostRepository;
import com.aqours_challenge.our_challenge.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    private final TagPostRepository tagPostRepository;
    private PostRepository postRepository;
    private TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository, TagPostRepository tagPostRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.tagPostRepository = tagPostRepository;
    }

    public List<Post> getAllPostNotDeleted() {
        return postRepository.getAllPostNotDeleted();
    }

    public Post savePost(Post post, List<String> tags) {
        Post newPost = new Post();

        if (isDoubledRequest(post)) {
            throw new IllegalStateException("동일한 게시물을 작성할 수 없습니다.");
        }
        try {
            newPost = postRepository.save(post);
            Tag newTag = new Tag();
            for (String tagName : tags) {
                if (tagName != null && !tagName.isEmpty()) {
                    newTag = tagRepository.findByTagName(tagName);
                    // if new tag
                    if (newTag == null) {
                        Tag tag = new Tag();
                        tag.setTagName(tagName);
                        newTag = tagRepository.save(tag);
                    }
                    // insert tag-post
                    tagPostRepository.save(TagPost.createTagPost(newTag.getTagId(), newPost.getPostId()));
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("태그 설정 오류");
        }

        return newPost;
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
