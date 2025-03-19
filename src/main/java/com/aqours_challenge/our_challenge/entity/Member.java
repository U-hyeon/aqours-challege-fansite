package com.aqours_challenge.our_challenge.entity;

import com.aqours_challenge.our_challenge.constant.Role;
import com.aqours_challenge.our_challenge.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String email;
    private String password;
    private String memberName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String gender;
    private String age;
    private String twitterId;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setMemberName(memberFormDto.getMemberName());
        member.setEmail(memberFormDto.getEmail());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);

        return member;
    }
}
