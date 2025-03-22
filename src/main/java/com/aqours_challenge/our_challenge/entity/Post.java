package com.aqours_challenge.our_challenge.entity;

import com.aqours_challenge.our_challenge.dto.PostFormDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId; // primary key

    @JoinColumn(name = "member_id")
    private Long memberId; // 작성자 id

    private String title; // 게시물 타이틀
    private String content; // 게시물 내용
    private String tags;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private String deleteFlag;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public static Post createPost(PostFormDto postFormDto) {
        Post post = new Post();
        post.setTitle(postFormDto.getTitle());
        post.setContent(postFormDto.getContent());
        post.setTags(postFormDto.getTags());
        post.setCreatedTime(LocalDateTime.now());
        post.setUpdatedTime(LocalDateTime.now());
        post.setDeleteFlag("N");

        return post;
    }
}
