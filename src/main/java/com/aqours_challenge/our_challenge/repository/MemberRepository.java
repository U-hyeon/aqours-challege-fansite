package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.constant.Gender;
import com.aqours_challenge.our_challenge.dto.MemberSearchResponse;
import com.aqours_challenge.our_challenge.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    MemberSearchResponse findMemberIdByEmail(String email);

    @Modifying
    @Query("update Member m " +
            "set m.password = :password, m.memberName = :memberName, m.twitterId = :twitterId " +
            ", m.gender = :gender, m.birthYear = :birthYear, m.countryCode = :countryCode " +
            "where m.email = :email ")
    int updateMemberInformation(String email, String password, String memberName, String twitterId
            , Gender gender, String birthYear, String countryCode);
}
