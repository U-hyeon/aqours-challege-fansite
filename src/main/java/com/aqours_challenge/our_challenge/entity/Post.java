package com.aqours_challenge.our_challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
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
}
