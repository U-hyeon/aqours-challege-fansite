package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Member;
import com.aqours_challenge.our_challenge.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("This E-mail is already signed.");
        }
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    /**
     * 회원정보 수정
     */
    public int changeMemberInfo(Member member) {
        return memberRepository.updateMemberInformation(member.getEmail()
                , member.getPassword(), member.getMemberName(), member.getTwitterId()
                , member.getGender(), member.getBirthYear(), member.getCountryCode());
    }

    // override UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member==null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
