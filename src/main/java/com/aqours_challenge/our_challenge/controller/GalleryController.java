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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            // 프로젝트 외부의 gallery 폴더 경로 설정
            Path workspaceDir = Paths.get("").toAbsolutePath().getParent();       // 리포지토리와 동일한 depth 에 저장
            Path galleryDir = workspaceDir.resolve("gallery");                    // gallery 디렉토리 안에 저장
            String filename = "gallery_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS")) + "_"
                    + String.format("%10s", memberId.toString()).replace(" ", "0")
                    + ".png";
            Path savePath = galleryDir.resolve(filename);

            Files.createDirectories(savePath.getParent());
            imageFile.transferTo(savePath.toFile());

            Img img = Img.createImg(memberId, galleryDir.toString(), filename);
            Img result = imgService.saveImage(img);

            return ResponseEntity.ok("Saved: " + result.getImg_file_name());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
