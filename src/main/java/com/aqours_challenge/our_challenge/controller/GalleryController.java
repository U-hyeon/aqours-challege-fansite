package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.entity.Img;
import com.aqours_challenge.our_challenge.service.ImgService;
import com.aqours_challenge.our_challenge.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
    private final MemberService memberService;
    private final ImgService imgService;

    public GalleryController(MemberService memberService, ImgService imgService) {
        this.memberService = memberService;
        this.imgService = imgService;
    }

    @GetMapping
    public String gallery(Model model) {
        return "gallery/search-image";
    }

    @GetMapping("/new")
    public String newImage() {
        return "gallery/make-image";
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadPost(@RequestParam("image") MultipartFile imageFile, Principal principal, Model model) {
        String currentUserEmail = principal.getName();
        if (currentUserEmail == null || currentUserEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Check your account");
        }

        Long memberId = memberService.findMemberByEmail(currentUserEmail).getMemberId();

        try {
            // 파일 저장 경로 (예: /uploads/2025-04-06_post.png)
            String filename = LocalDate.now() + memberId.toString() + "_gallery.png";
            String location = "gallery";

            Path savePath = Paths.get(location, filename);
            Files.createDirectories(savePath.getParent());
            imageFile.transferTo(savePath.toFile());

            Img img = Img.createImg(memberId, location, filename);
            Img result = imgService.saveImage(img);

            return ResponseEntity.ok("Saved: " + result.getImg_file_name());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
