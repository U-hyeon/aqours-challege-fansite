package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.dto.ImgDto;
import com.aqours_challenge.our_challenge.entity.Img;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ImgRepository extends JpaRepository<Img, Long> {
    @Query(value = "select new com.aqours_challenge.our_challenge.dto.ImgDto(i.img_id, i.img_file_name) " +
            "from Img i " +
            "where i.deleteFlag='N' order by i.createdTime desc")
    Page<ImgDto> getGalleryImages(ImgDto imgDto, Pageable pageable);
}
