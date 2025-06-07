package com.aqours_challenge.our_challenge.dto;

import java.math.BigDecimal;

/**
 * 신규 RpPost 작성 요청
 */
public class RpPostSaveRequest {
    /**
     * 카테고리
     */
    private String category;
    /**
     * 텍스트 내용
     */
    private String text;
    /**
     * 텍스트 정렬
     */
    private String textAlign;
    /**
     * 텍스트 컬러 ( rgb(255, 255, 255) )
     */
    private String textColor;
    /**
     * 텍스트 크기
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
     * 요청한 유저의 이메일
     */
    private String userEmail;

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public String getTextColor() {
        return textColor;
    }

    public BigDecimal getScale() {
        return scale;
    }

    public BigDecimal getPositionX() {
        return positionX;
    }

    public BigDecimal getPositionY() {
        return positionY;
    }

    public BigDecimal getRotationZ() {
        return rotationZ;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
