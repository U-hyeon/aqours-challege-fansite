package com.aqours_challenge.our_challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
