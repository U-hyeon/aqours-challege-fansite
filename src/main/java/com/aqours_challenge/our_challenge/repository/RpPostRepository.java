package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.RpPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RpPostRepository extends JpaRepository<RpPost, Long> {
    /**
     * 렌더링에 필요한 최소 정보들만 반환
     */
    List<RpPost> findByCategoryAndIsDeletedOrderByRegTime(String category, String isDeleted);
}
