package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.MemberFormDto;
import com.aqours_challenge.our_challenge.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final MemberService memberService;

    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String validatePage(Principal principal, Model model) {
        String currentUserEmail = principal.getName();

        model.addAttribute("currentUserEmail", currentUserEmail);
        return "mypage/validate-page";
    }
}
