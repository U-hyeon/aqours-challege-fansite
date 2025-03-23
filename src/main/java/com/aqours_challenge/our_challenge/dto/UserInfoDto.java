package com.aqours_challenge.our_challenge.dto;

import com.aqours_challenge.our_challenge.constant.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.lang.reflect.Type;

public class UserInfoDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message="이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message="비밀번호는 8~16자 이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String memberName;

    @NotBlank(message = "트위터 아이디는 필수 입력 값입니다.")
    private String twitterId;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String birthYear;
    @Length(min=2, max=2, message="비밀번호는 8~16자 이어야 합니다.")
    private String countryCode;

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

    public @NotBlank(message = "닉네임은 필수 입력 값입니다.") String getMemberName() {
        return memberName;
    }

    public void setMemberName(@NotBlank(message = "닉네임은 필수 입력 값입니다.") String memberName) {
        this.memberName = memberName;
    }

    public @NotBlank(message = "트위터 아이디는 필수 입력 값입니다.") String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(@NotBlank(message = "트위터 아이디는 필수 입력 값입니다.") String twitterId) {
        this.twitterId = twitterId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public @Length(min = 2, max = 2, message = "비밀번호는 8~16자 이어야 합니다.") String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(@Length(min = 2, max = 2, message = "비밀번호는 8~16자 이어야 합니다.") String countryCode) {
        this.countryCode = countryCode;
    }
}
