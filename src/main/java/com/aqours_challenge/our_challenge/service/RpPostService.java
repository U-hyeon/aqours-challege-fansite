package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.dto.MemberSearchResponse;
import com.aqours_challenge.our_challenge.dto.RpPostSaveRequest;
import com.aqours_challenge.our_challenge.entity.RpPost;
import com.aqours_challenge.our_challenge.repository.MemberRepository;
import com.aqours_challenge.our_challenge.repository.RpPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class RpPostService {
    private final RpPostRepository rpPostRepository;
    private final MemberRepository memberRepository;

    public RpPostService(RpPostRepository rpPostRepository, MemberRepository memberRepository) {
        this.rpPostRepository = rpPostRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * rp_post 테이블로 엔티티 저장
     */
    public RpPost saveRpPost(RpPostSaveRequest rpPostSaveRequest) {
        RpPost rpPost = new RpPost();
        // DTO 로부터 주요데이터 저장
        rpPost.setCategory(rpPostSaveRequest.getCategory());
        rpPost.setTextContent(rpPostSaveRequest.getText());
        rpPost.setTextAlign(rpPostSaveRequest.getTextAlign());
        rpPost.setTextColor(rpPostSaveRequest.getTextColor());
        rpPost.setScale(BigDecimal.valueOf(rpPostSaveRequest.getScale().doubleValue()));
        rpPost.setPositionX(BigDecimal.valueOf(rpPostSaveRequest.getPositionX().doubleValue()));
        rpPost.setPositionY(BigDecimal.valueOf(rpPostSaveRequest.getPositionY().doubleValue()));
        rpPost.setRotationZ(BigDecimal.valueOf(rpPostSaveRequest.getRotationZ().doubleValue()));

        // 그 외 데이터 자동 작성
        MemberSearchResponse userCd = memberRepository.findMemberIdByEmail(rpPostSaveRequest.getUserEmail());
        rpPost.setRegUser(userCd.getMemberId());
        rpPost.setModifyUser(userCd.getMemberId());

        return rpPostRepository.save(rpPost); // Save the RpPost using the repository
    }
}
