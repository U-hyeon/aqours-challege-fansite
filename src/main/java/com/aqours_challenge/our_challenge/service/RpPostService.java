package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.dto.MemberSearchResponse;
import com.aqours_challenge.our_challenge.dto.RpPostResponse;
import com.aqours_challenge.our_challenge.dto.RpPostSaveRequest;
import com.aqours_challenge.our_challenge.entity.RpPost;
import com.aqours_challenge.our_challenge.repository.MemberRepository;
import com.aqours_challenge.our_challenge.repository.RpPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RpPostService {
    private final RpPostRepository rpPostRepository;
    private final MemberRepository memberRepository;

    public RpPostService(RpPostRepository rpPostRepository, MemberRepository memberRepository) {
        this.rpPostRepository = rpPostRepository;
        this.memberRepository = memberRepository;
    }

    public List<RpPostResponse> findByCategory(String category) {
        List<RpPost> rpPosts = rpPostRepository.findByCategoryAndIsDeleted(category, "N");
        List<RpPostResponse> rpPostResponses = new ArrayList<>();

        for (RpPost rpPost : rpPosts) {
            RpPostResponse response = new RpPostResponse(
                    rpPost.getRpPostId(),
                    rpPost.getTextContent(),
                    rpPost.getTextAlign(),
                    rpPost.getTextColor(),
                    rpPost.getScale(),
                    rpPost.getPositionX(),
                    rpPost.getPositionY(),
                    rpPost.getRotationZ(),
                    rpPost.getRegUser(),
                    rpPost.getRegTime()
            );
            rpPostResponses.add(response);
        }

        return rpPostResponses;
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

        return rpPostRepository.save(rpPost);
    }
}
