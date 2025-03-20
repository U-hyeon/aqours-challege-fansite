package com.aqours_challenge.our_challenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostFormDto {
    private String postId;

    @NotBlank(message = "로그인이 필요합니다.")
    private Long memberId;

    @NotBlank(message = "제목을 입력하세요.")
    @Length(max=30, message = "제목은 최대 30자입니다.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    @Length(max=200, message = "최대 작성 가능한 길이는 200자 입니다.")
    private String content;

    @Length(max=55, message = "태그정보는 총 50자 까지입니다.")
    private String tags;
}
