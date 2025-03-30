package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.TagPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TagPostRepository extends JpaRepository<TagPost, Long> {

}
