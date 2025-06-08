package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.dto.ImgDto;
import com.aqours_challenge.our_challenge.entity.Img;
import com.aqours_challenge.our_challenge.service.ImgService;
import com.aqours_challenge.our_challenge.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
    private final MemberService memberService;
    private final ImgService imgService;

    /**
     * 물리적 이미지파일 디렉토리
     */
    @Value("${image.root.path}")
    private String actualImageDirectory;

    public GalleryController(MemberService memberService, ImgService imgService) {
        this.memberService = memberService;
        this.imgService = imgService;
    }

    /**
     * 갤러리 이미지 목록/검색 페이지
     */
//    @GetMapping
//    public String gallery(ImgDto imgDto, Optional<Integer> page, Model model) {
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
//        Page<ImgDto> imgPage = imgService.getGalleryImages(imgDto, pageable);
//        model.addAttribute("images", imgPage);
//
//        return "gallery/search-image";
//    }

    /**
     * 이미지파일 생성 페이지
     */
//    @GetMapping("/new")
//    public String newImage() {
//        return "gallery/make-image";
//    }

    /**
     * 이미지 파일을 서버에 저장
     */
    @PostMapping("/image")
    public ResponseEntity<String> uploadPost(@RequestParam("image") MultipartFile imageFile, Principal principal, Model model) {
        String currentUserEmail = principal.getName();
        if (currentUserEmail == null || currentUserEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Check your account");
        }

        Long memberId = memberService.findMemberByEmail(currentUserEmail).getMemberId();

        try {
            // 프로젝트 외부의 gallery 폴더 경로 설정
            String filename = "gallery_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS")) + "_"
                    + String.format("%10s", memberId.toString()).replace(" ", "0")
                    + ".png";
            Path galleryDir = imgService.getActualImageDirectoryPath("gallery");
            Path savePath = galleryDir.resolve(filename);

            Files.createDirectories(savePath.getParent());
            imageFile.transferTo(savePath.toFile());

            Img img = Img.createImg(memberId, "gallery", filename);
            Img result = imgService.saveImage(img);

            return ResponseEntity.ok("Saved: " + result.getImg_file_name());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
