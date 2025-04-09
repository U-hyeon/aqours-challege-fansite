package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Img;
import com.aqours_challenge.our_challenge.repository.ImgRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImgService {
    ImgRepository imgRepository;
    public ImgService(ImgRepository imgRepository) {
        this.imgRepository = imgRepository;
    }

    public Img saveImage(Img img) {
        return imgRepository.save(img);
    }
}
