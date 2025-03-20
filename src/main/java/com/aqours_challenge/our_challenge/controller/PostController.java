package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/search")
    public String getPosts(Model model) {
        // 게시물 리스트 전달
        List<Post> postList = new ArrayList<>();
        postList = postService.getAllPosts();

        model.addAttribute("postList", postList);

        return "post/search-post";
    }

    @GetMapping("/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "post/new-post";
    }
}
