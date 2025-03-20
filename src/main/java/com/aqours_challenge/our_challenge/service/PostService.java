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

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
