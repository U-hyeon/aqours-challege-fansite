package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.repository.TagPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagPostService {
    private final TagPostRepository tagPostRepository;

    public TagPostService(TagPostRepository tagPostRepository) {
        this.tagPostRepository = tagPostRepository;
    }

    public List<String> getTagsOfPost(Post post) {
        return tagPostRepository.getTagPostsByPostId(post.getPostId());
    }
}
