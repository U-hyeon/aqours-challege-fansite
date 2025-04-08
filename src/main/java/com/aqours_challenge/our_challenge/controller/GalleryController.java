package com.aqours_challenge.our_challenge.controller;

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
import java.time.LocalDate;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
    @GetMapping
    public String gallery(Model model) {
        return "gallery/search-image";
    }

    @GetMapping("/new")
    public String newImage() {
        return "post/make-image";
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadPost(@RequestParam("image") MultipartFile imageFile) {
        try {
            // 파일 저장 경로 (예: /uploads/2025-04-06_post.png)
            String filename = LocalDate.now() + "_post.png";
            Path savePath = Paths.get("uploads", filename);
            Files.createDirectories(savePath.getParent());
            imageFile.transferTo(savePath.toFile());

            return ResponseEntity.ok("Saved: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
