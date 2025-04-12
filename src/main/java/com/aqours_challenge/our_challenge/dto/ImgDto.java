package com.aqours_challenge.our_challenge.dto;

public class ImgDto {
    private Long imgId;
    private String imgFileName;

    public ImgDto(Long imgId, String imgFileName) {
        this.imgId = imgId;
        this.imgFileName = imgFileName;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }
}
