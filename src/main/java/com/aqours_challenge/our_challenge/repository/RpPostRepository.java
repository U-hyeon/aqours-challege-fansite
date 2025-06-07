package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.RpPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface RpPostRepository extends JpaRepository<RpPost, Long> {
}
