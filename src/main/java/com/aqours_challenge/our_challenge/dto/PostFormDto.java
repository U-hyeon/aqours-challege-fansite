package com.aqours_challenge.our_challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class PostFormDto {
    @NotBlank(message = "제목을 입력하세요.")
    @Length(max=30, message = "제목은 최대 30자입니다.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    @Length(max=200, message = "최대 작성 가능한 길이는 200자 입니다.")
    private String content;

    @Size(max = 5, message = "태그는 최대 5개까지만 지정 가능합니다.")
    private List<@Length(max=20, message = "태그 최대 길이는 20자 입니다.") String> tags;

    public @NotBlank(message = "제목을 입력하세요.") @Length(max = 30, message = "제목은 최대 30자입니다.") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "제목을 입력하세요.") @Length(max = 30, message = "제목은 최대 30자입니다.") String title) {
        this.title = title;
    }

    public @NotBlank(message = "내용을 입력하세요.") @Length(max = 200, message = "최대 작성 가능한 길이는 200자 입니다.") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "내용을 입력하세요.") @Length(max = 200, message = "최대 작성 가능한 길이는 200자 입니다.") String content) {
        this.content = content;
    }

    public @Size(max = 5, message = "태그는 최대 5개까지만 지정 가능합니다.") List<@Length(max = 20, message = "태그 최대 길이는 20자 입니다.") String> getTags() {
        return tags;
    }

    public void setTags(@Size(max = 5, message = "태그는 최대 5개까지만 지정 가능합니다.") List<@Length(max = 20, message = "태그 최대 길이는 20자 입니다.") String> tags) {
        this.tags = tags;
    }
}
