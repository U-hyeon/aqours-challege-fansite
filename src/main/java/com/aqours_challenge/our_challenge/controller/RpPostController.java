package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.RpPostSaveRequest;
import com.aqours_challenge.our_challenge.entity.RpPost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class RpPostController {

    @GetMapping("/yosegaki")
    public String rpPost() {
        return "yosegaki/yosegaki";
    }

    @RequestMapping(value = "/api/yosegaki/new", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String newRpPost(@RequestBody RpPostSaveRequest rpPostSaveRequest, Principal principal) {
        return "success";
    }

}
