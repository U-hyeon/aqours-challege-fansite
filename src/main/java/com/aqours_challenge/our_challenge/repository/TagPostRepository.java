package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.TagPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TagPostRepository extends JpaRepository<TagPost, Long> {
    @Query(value = "select t.tagName from TagPost tp inner join Tag t on tp.tagId = t.tagId " +
            "where tp.postId = :postId")
    List<String> getTagPostsByPostId(Long postId);
}
