package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);

    @Query("select p from Post p " +
            "where p.title like %:keyword% " +
            "or p.content like %:keyword% " +
            "order by p.postId desc")
    List<Post> findByTitleAndContentKeyword(@Param("keyword") String keyword);

    @Query("select p from Post p " +
            "where p.memberId in (" +
            "select m.memberId from Member m " +
            "where m.memberName like %:keyword%) " +
            "order by p.postId desc")
    List<Post> findByMemberNameKeyword(@Param("keyword") String keyword);
}
