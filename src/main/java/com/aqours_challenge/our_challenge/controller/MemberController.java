package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.LoginDto;
import com.aqours_challenge.our_challenge.dto.MemberFormDto;
import com.aqours_challenge.our_challenge.dto.UserInfoDto;
import com.aqours_challenge.our_challenge.entity.Member;
import com.aqours_challenge.our_challenge.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        // 메인페이지로 이동
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginForm(Model model) {
        return "member/loginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "* Please check your ID and Password");
        return "member/loginForm";
    }

    @PostMapping(value="/validate", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> validate(@RequestBody LoginDto loginDto) {
        Map<String, Object> result = new HashMap<>();

        Member currentUser = memberService.findMemberByEmail(loginDto.getEmail());
        if (currentUser == null) {
            result.put("error", "No such user");
            return ResponseEntity
                    .ok()
                    .body(result);
        }

        String truePassword = currentUser.getPassword();
        if ( !passwordEncoder.matches(loginDto.getPassword(), truePassword) ) {
            result.put("error", "Invalid password");
        } else {
            result.put("status", "success");
        }

        return ResponseEntity
                .ok()
                .body(result);
    }

    @PostMapping(value = "/change-member-info", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> changeMemberInfo(@RequestBody UserInfoDto userInfoDto) {
        Map<String, Object> result = new HashMap<>();

        Member newMemberInfo = new Member();
        newMemberInfo.setEmail(userInfoDto.getEmail());
        newMemberInfo.setPassword(userInfoDto.getPassword(), passwordEncoder);
        newMemberInfo.setMemberName(userInfoDto.getMemberName());
        newMemberInfo.setTwitterId(userInfoDto.getTwitterId());
        newMemberInfo.setGender(userInfoDto.getGender());
        newMemberInfo.setBirthYear(userInfoDto.getBirthYear());
        newMemberInfo.setCountryCode(userInfoDto.getCountryCode());
        if (memberService.changeMemberInfo(newMemberInfo) > 0) {
            result.put("status", "success");
            return ResponseEntity
                    .ok()
                    .body(result);
        }

        result.put("status", "failed");
        return ResponseEntity
                .ok()
                .body(result);
    }
}
