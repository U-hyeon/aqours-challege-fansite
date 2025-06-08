package com.aqours_challenge.our_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        return "main";
    }

    @GetMapping("/loading")
    public String LoadingPage(Model model) {
        model.addAttribute("status", "로딩중...");
        return "loading";
    }

    @PostMapping(value = "/api/check-logined", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> checkLogined(Principal principal) {
        Map<String, Object> result = new HashMap<>();

        if (principal == null) {
            result.put("error", "Please login");
            return ResponseEntity
                    .ok()
                    .body(result);
        }

        String userEmail = principal.getName();
        if (userEmail == null || userEmail.isEmpty()) {
            result.put("error", "Please login");
            return ResponseEntity
                    .ok()
                    .body(result);
        }

        result.put("status", "success");
        return ResponseEntity
                .ok()
                .body(result);
    }
}
