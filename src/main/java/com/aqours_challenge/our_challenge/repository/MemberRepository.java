package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
