package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.PostFormDto;
import com.aqours_challenge.our_challenge.entity.Member;
import com.aqours_challenge.our_challenge.entity.Post;
import com.aqours_challenge.our_challenge.service.MemberService;
import com.aqours_challenge.our_challenge.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    public PostController(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @GetMapping("/search")
    public String getPosts(Model model) {
        // 게시물 리스트 전달
        List<Post> postList = new ArrayList<>();
        postList = postService.getAllPostNotDeleted();

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
            postService.savePost(post);
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
        model.addAttribute("memberName", memberName);
        return "post/detail-post";
    }

//    @DeleteMapping
//    public ResponseEntity<Map<String, Object>> deletePost(@RequestBody HashMap<String, Object> params) {
//        Map<String, Object> result = new HashMap<>();
//
//        result.put("message", "게시물 삭제 처리");
//        if (params == null || params.isEmpty() || params.get("postId") == null) {
//            result.put("error", "Invalid parameters");
//            return ResponseEntity.badRequest().body(result);
//        }
//
//        int resultQuery = postService.deletePost(params.get("postId").toString());
//
//        if (resultQuery == 0) {
//            result.put("error", "Post not found");
//            return ResponseEntity.badRequest().body(result);
//        }
//
//        result.put("status", resultQuery + "개의 게시물 삭제완료");
//        return ResponseEntity
//                .ok()
//                .body(result);
//    }
}
