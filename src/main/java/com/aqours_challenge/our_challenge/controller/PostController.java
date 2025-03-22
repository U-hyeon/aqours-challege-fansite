package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.MemberFormDto;
import com.aqours_challenge.our_challenge.dto.PostFormDto;
import com.aqours_challenge.our_challenge.entity.Member;
import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("postFormDto", new PostFormDto());
        return "post/new-post";
    }

    @PostMapping("/new")
    public String saveNewPost(@Valid PostFormDto postFormDto,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "post/new-post";
        }

        try {
            Post post = Post.createPost(postFormDto);
            postService.savePost(post);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "post/new-post";
        }

        // 게시물 목록으로 이동
        return "redirect:/posts/search";
    }
}
