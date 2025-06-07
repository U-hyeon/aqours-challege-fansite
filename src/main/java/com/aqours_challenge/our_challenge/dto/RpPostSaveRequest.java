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

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public String getTextAlign() {
        return textAlign;
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
}
