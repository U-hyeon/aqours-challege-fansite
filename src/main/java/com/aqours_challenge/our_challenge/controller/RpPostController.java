package com.aqours_challenge.our_challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/yosegaki")
public class RpPostController {
    @GetMapping
    public String rpPost() {
        return "yosegaki/yosegaki";
    }
}
