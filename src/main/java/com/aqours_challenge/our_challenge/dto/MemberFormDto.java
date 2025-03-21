package com.aqours_challenge.our_challenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * <sl>
 *     <li>회원명</li>
 *     <li>이메일</li>
 *     <li>비밀번호</li>
 *     <li>주소</li>
 * </sl>
 */
public class MemberFormDto {
    /**
     * 회원명
     */
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String memberName;

    /**
     * 이메일
     */
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message="이메일 형식으로 입력해주세요.")
    private String email;

    /**
     * 비밀번호
     */
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message="비밀번호는 8~16자 이어야 합니다.")
    private String password;

    /**
     * 주소
     */
    @NotBlank(message = "트위터 아이디는 필수 입력 값입니다.")
    private String twitterId;

    public @NotBlank(message = "닉네임은 필수 입력 값입니다.") String getMemberName() {
        return memberName;
    }

    public void setMemberName(@NotBlank(message = "닉네임은 필수 입력 값입니다.") String memberName) {
        this.memberName = memberName;
    }

    public @NotBlank(message = "이메일은 필수 입력 값입니다.") @Email(message = "이메일 형식으로 입력해주세요.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "이메일은 필수 입력 값입니다.") @Email(message = "이메일 형식으로 입력해주세요.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "비밀번호는 필수 입력 값입니다.") @Length(min = 8, max = 16, message = "비밀번호는 8~16자 이어야 합니다.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "비밀번호는 필수 입력 값입니다.") @Length(min = 8, max = 16, message = "비밀번호는 8~16자 이어야 합니다.") String password) {
        this.password = password;
    }

    public @NotBlank(message = "트위터 아이디는 필수 입력 값입니다.") String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(@NotBlank(message = "트위터 아이디는 필수 입력 값입니다.") String twitterId) {
        this.twitterId = twitterId;
    }
}
