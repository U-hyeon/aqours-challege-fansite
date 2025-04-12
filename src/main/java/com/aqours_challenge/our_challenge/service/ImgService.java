package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.dto.ImgDto;
import com.aqours_challenge.our_challenge.entity.Img;
import com.aqours_challenge.our_challenge.repository.ImgRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Paths;

@Service
@Transactional
public class ImgService {
    public String galleryRootPath = Paths.get("").toAbsolutePath().getParent().resolve("gallery").toString();

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
}
