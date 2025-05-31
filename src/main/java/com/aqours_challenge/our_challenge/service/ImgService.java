package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.dto.ImgDto;
import com.aqours_challenge.our_challenge.entity.Img;
import com.aqours_challenge.our_challenge.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class ImgService {
    /**
     * 물리적 이미지파일 디렉토리
     */
    @Value("${image.root.path}")
    private String actualImageDirectory;


    ImgRepository imgRepository;
    public ImgService(ImgRepository imgRepository) {
        this.imgRepository = imgRepository;
    }

    public Img saveImage(Img img) {
        return imgRepository.save(img);
    }

    @Transactional(readOnly = true)
    public Page<ImgDto> getGalleryImages(ImgDto imgDto, Pageable pageable) {
        Page<ImgDto> all = imgRepository.getGalleryImages(imgDto, pageable);

        return all;
    }

    @Transactional(readOnly = true)
    public ImgDto getGalleryImageById(Long id) {
        Img img = imgRepository.getById(id);
        ImgDto imgDto = new ImgDto(id, img.getImg_file_name());
        return imgDto;
    }

    public Path getActualImageDirectoryPath(String subDirectoryPath) {
        // 프로젝트 외부의 gallery 폴더 경로 설정
        Path workspaceDir = Paths.get("").toAbsolutePath();       // 리포지토리와 동일한 depth 에 저장
        return workspaceDir.resolve( actualImageDirectory + "/" + subDirectoryPath);                    // gallery 디렉토리 안에 저장
    }
}
