package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.PostFormDto;
import com.aqours_challenge.our_challenge.entity.Member;
import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.service.MemberService;
import com.aqours_challenge.our_challenge.service.PostService;
import com.aqours_challenge.our_challenge.service.TagPostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;
    private final TagPostService tagPostService;

    public PostController(PostService postService, MemberService memberService, TagPostService tagPostService) {
        this.postService = postService;
        this.memberService = memberService;
        this.tagPostService = tagPostService;
    }

    @GetMapping("/search")
    public String getPosts(@RequestParam(required = false) String keyword, Model model) {
        // 게시물 리스트 전달
        List<Post> postList = new ArrayList<>();
        if (keyword == null) {
            postList = postService.getAllPostNotDeleted();
        } else {
            postList = postService.getPostsByKeyword(keyword);
        }
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
                              BindingResult bindingResult, Principal principal, Model model) {
        // 게시물 데이터 검증
        if(bindingResult.hasErrors()) {
            return "post/new-post";
        }

        // 작성자 정보 가져오기
        Member currentMember = memberService.findMemberByEmail(principal.getName());
        if(currentMember == null) {
            // 로그인 페이지로 이동
            return "redirect:/members/login";
        }

        try {
            Post post = Post.createPost(currentMember.getMemberId(), postFormDto);
            postService.savePost(post, postFormDto.getTags());
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "post/new-post";
        }

        // 게시물 목록으로 이동
        return "redirect:/posts/search";
    }

    @GetMapping("/detail/{strPostId}")
    public String detailPost(@PathVariable String strPostId, Model model, Principal principal) {
        Long postId = Long.parseLong(strPostId);
        Post post = postService.getPostByPostId(postId);
        if (!post.getDeleteFlag().equals("N")) {
            return "customError/deleted-post";
        }
        String memberName = memberService.findMemberByEmail(principal.getName()).getMemberName();
        model.addAttribute("post", post);
        model.addAttribute("tags", tagPostService.getTagsOfPost(post));
        model.addAttribute("memberName", memberName);
        return "post/detail-post";
    }

    @GetMapping("/image")
    public String imgPost() {
        return "post/make-image";
    }
}
