package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.constant.Gender;
import com.aqours_challenge.our_challenge.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
