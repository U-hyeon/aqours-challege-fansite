package com.aqours_challenge.our_challenge.entity;

import com.aqours_challenge.our_challenge.constant.Gender;
import com.aqours_challenge.our_challenge.constant.Role;
import com.aqours_challenge.our_challenge.dto.MemberFormDto;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 필수정보 ~start
    private String email;
    private String password;
    private String memberName;
    private String twitterId;
    // ~end 필수정보

    // 부가정보 ~start
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String birthYear;
    private String countryCode;
    // ~end 부가정보

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    /**
     * 필수정보를 통한 신규회원 생성
     */
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setRole(Role.USER);
        member.setEmail(memberFormDto.getEmail());
        member.setPassword(memberFormDto.getPassword(), passwordEncoder);
        member.setMemberName(memberFormDto.getMemberName());
        member.setTwitterId(memberFormDto.getTwitterId());

        return member;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String country) {
        this.countryCode = country;
    }
}
