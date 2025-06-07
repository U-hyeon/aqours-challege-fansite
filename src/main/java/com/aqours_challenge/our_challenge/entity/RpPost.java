package com.aqours_challenge.our_challenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 롤링페이퍼 작성된 데이터 테이블
 */
@Entity
@Table(name = "rp_post")
public class RpPost {
    /**
     * 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rpPostId;

    /**
     * 카테고리
     */
    @Column(name = "category", length = 10, nullable = false)
    @NotBlank
    private String category;
    /**
     * 텍스트 내용
     */
    @Column(name = "text_content", length = 100, nullable = false)
    @NotBlank
    private String textContent;
    /**
     * 텍스트 정렬
     */
    @Column(name = "text_align", length = 6)
    private String textAlign;
    /**
     * 텍스트 크기
     */
    @Column(name = "scale", precision = 10, scale = 5)
    private BigDecimal scale;
    /**
     * 텍스트 위치 X
     */
    @Column(name = "position_x", precision = 10, scale = 4)
    private BigDecimal positionX;
    /**
     * 텍스트 위치 Y
     */
    @Column(name = "position_y", precision = 10, scale = 4)
    private BigDecimal positionY;
    /**
     * 텍스트 회전 Z축
     */
    @Column(name = "rotation_z", precision = 10, scale = 4)
    private BigDecimal rotationZ;

    /**
     * 작성자
     */
    @Column(name = "reg_user")
    @JoinColumn(name = "member_id")
    private Long regUser;
    /**
     * 작성일시
     */
    @Column(name = "reg_time")
    private LocalDateTime regTime;
    /**
     * 수정자
     */
    @Column(name = "modify_user")
    @JoinColumn(name = "member_id")
    private Long modifyUser;
    /**
     * 수정일시
     */
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;
    /**
     * Y: 삭제, N: 삭제되지 않음
     */
    @Column(name = "is_deleted", length = 1, nullable = false)
    private String isDeleted;

    public Long getRpPostId() {
        return rpPostId;
    }

    public void setRpPostId(Long rpPostId) {
        this.rpPostId = rpPostId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}
