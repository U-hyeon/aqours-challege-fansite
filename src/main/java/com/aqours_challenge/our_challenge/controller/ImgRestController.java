package com.aqours_challenge.our_challenge.controller;

import com.aqours_challenge.our_challenge.service.ImgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ImgRestController {
    private final ImgService imgService;
    public ImgRestController(ImgService imgService) {
        this.imgService = imgService;
    }

    /**
     * 썸네일의 이미지 id를 통하여 원본 이미지 파일데이터를 반환
     */
    @PostMapping(value = "/api/image", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> getSelectedImage(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>(params);
        try {
            // params:imageId
            Long imageId = Long.parseLong(map.get("imageId").toString()); // Long으로 변환
            result.put("image", imgService.getGalleryImageById(imageId));
            result.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok().body(result);
    }
}
