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
    @NotBlank(message = "Username must be filled.")
    private String memberName;

    /**
     * 이메일
     */
    @NotBlank(message = "E-mail must be filled.")
    @Email(message="Please use an E-mail.")
    private String email;

    /**
     * 비밀번호
     */
    @NotBlank(message = "Password must be filled.")
    @Length(min=8, max=16, message="Please use password in 8~16 characters.")
    private String password;

    /**
     * 주소
     */
    @NotBlank(message = "Twitter id must be filled.")
    private String twitterId;

    public @NotBlank(message = "Username must be filled.") String getMemberName() {
        return memberName;
    }

    public void setMemberName(@NotBlank(message = "Username must be filled.") String memberName) {
        this.memberName = memberName;
    }

    public @NotBlank(message = "E-mail must be filled.") @Email(message = "Please use an E-mail.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "E-mail must be filled.") @Email(message = "Please use an E-mail.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password must be filled.") @Length(min = 8, max = 16, message = "Please use password in 8~16 characters.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password must be filled.") @Length(min = 8, max = 16, message = "Please use password in 8~16 characters.") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Twitter id must be filled.") String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(@NotBlank(message = "Twitter id must be filled.") String twitterId) {
        this.twitterId = twitterId;
    }
}
