package com.aqours_challenge.our_challenge.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "img")
public class Img {
    @Id
    @Column(name = "img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long img_id;
    private Long member_id;
    private String img_location;
    private String img_file_name;
    private LocalDateTime createdTime;
    private String deleteFlag;

    public Long getImg_id() {
        return img_id;
    }

    public void setImg_id(Long img_id) {
        this.img_id = img_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getImg_location() {
        return img_location;
    }

    public void setImg_location(String location) {
        this.img_location = location;
    }

    public String getImg_file_name() {
        return img_file_name;
    }

    public void setImg_file_name(String img_file_name) {
        this.img_file_name = img_file_name;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public static Img createImg(Long memberId, String location, String img_file_name) {
        Img img = new Img();
        img.setMember_id(memberId);
        img.setImg_location(location);
        img.setImg_file_name(img_file_name);
        img.setCreatedTime(LocalDateTime.now());
        img.setDeleteFlag("N");

        return img;
    }
}
