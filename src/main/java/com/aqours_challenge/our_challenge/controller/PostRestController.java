package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/api/posts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> deletePost(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();

//        String postId = "2";
        result.put("message", "게시물 삭제 처리");
        if (params == null || params.isEmpty() || params.get("postId") == null) {
//        if (postId == null || postId.isEmpty()) {
            result.put("error", "Invalid parameters");
            return ResponseEntity.badRequest().body(result);
        }

        int resultQuery = postService.deletePost(params.get("postId").toString());

        if (resultQuery == 0) {
            result.put("error", "Post not found");
            return ResponseEntity.badRequest().body(result);
        }

        result.put("status", resultQuery + "개의 게시물 삭제완료");
        return ResponseEntity
                .ok()
                .body(result);
    }
}
