package com.aqours_challenge.our_challenge.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * RpPost 필수데이터 조회
 */
public class RpPostResponse {
    /**
     * 기본키
     */
    private Long rpPostId;
    /**
     * 텍스트 내용
     */
    private String textContent;
    /**
     * 텍스트 정렬
     */
    private String textAlign;
    /**
     * 텍스트 컬러
     */
    private String textColor;
    /**
     * 텍스트 사이즈 (px)
     */
    private BigDecimal textSize;
    /**
     * 텍스트박스 크기
     */
    private BigDecimal scale;
    /**
     * 텍스트 위치 X
     */
    private BigDecimal positionX;
    /**
     * 텍스트 위치 Y
     */
    private BigDecimal positionY;
    /**
     * 텍스트 회전 Z축
     */
    private BigDecimal rotationZ;
    /**
     * 작성자
     */
    private Long regUser;
    /**
     * 작성일시
     */
    private LocalDateTime regTime;

    public RpPostResponse(
            Long rpPostId, String textContent,
            String textAlign, String textColor,
            BigDecimal textSize, BigDecimal scale,
            BigDecimal positionX, BigDecimal positionY,
            BigDecimal rotationZ, Long regUserId,
            LocalDateTime regTime) {
        this.rpPostId = rpPostId;
        this.textContent = textContent;
        this.textAlign = textAlign;
        this.textColor = textColor;
        this.textSize = textSize;
        this.scale = scale;
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotationZ = rotationZ;
        this.regUser = regUserId;
        this.regTime = regTime;
    }

    public Long getRpPostId() {
        return rpPostId;
    }

    public void setRpPostId(Long rpPostId) {
        this.rpPostId = rpPostId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public BigDecimal getTextSize() {
        return textSize;
    }

    public void setTextSize(BigDecimal textSize) {
        this.textSize = textSize;
    }

    public BigDecimal getScale() {
        return scale;
    }

    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    public BigDecimal getPositionX() {
        return positionX;
    }

    public void setPositionX(BigDecimal positionX) {
        this.positionX = positionX;
    }

    public BigDecimal getPositionY() {
        return positionY;
    }

    public void setPositionY(BigDecimal positionY) {
        this.positionY = positionY;
    }

    public BigDecimal getRotationZ() {
        return rotationZ;
    }

    public void setRotationZ(BigDecimal rotationZ) {
        this.rotationZ = rotationZ;
    }

    public Long getRegUser() {
        return regUser;
    }

    public void setRegUser(Long regUser) {
        this.regUser = regUser;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }
}
