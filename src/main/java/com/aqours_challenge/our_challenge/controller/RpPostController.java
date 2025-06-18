package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.RpPostResponse;
import com.aqours_challenge.our_challenge.dto.RpPostSaveRequest;
import com.aqours_challenge.our_challenge.service.RpPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class RpPostController {

    private final RpPostService rpPostService;

    public RpPostController(RpPostService rpPostService) {
        this.rpPostService = rpPostService;
    }

    @GetMapping("/yosegaki")
    public String rpPost() {
        return "yosegaki/yosegaki";
    }

    @RequestMapping(value = "/api/yosegaki/new", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String newRpPost(@RequestBody RpPostSaveRequest rpPostSaveRequest, Principal principal) {
        if (principal != null) {
            rpPostSaveRequest.setUserEmail(principal.getName());
        }
        rpPostService.saveRpPost(rpPostSaveRequest);
        return "success";
    }

    @RequestMapping(value = "/api/yosegaki/from-category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RpPostResponse> fromCategory(@RequestBody String category) {
        List<RpPostResponse> result = rpPostService.findByCategory(category);

        return result;
    }
}
