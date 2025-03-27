package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);

    @Query("select p from Post p " +
            "where p.deleteFlag = 'N' " +
            "order by p.postId asc")
    List<Post> getAllPostNotDeleted();

    @Modifying
    @Transactional
    @Query("update Post p " +
            "set p.deleteFlag = 'Y' " +
            "where p.postId = :postId")
    int deletePost(@Param("postId") Long postId);

    @Query("select p from Post p " +
            "where p.title like %:keyword% " +
            "or p.content like %:keyword% " +
            "or p.tags like %:keyword% " +
            "order by p.postId desc")
    List<Post> findByTitleAndContentKeyword(@Param("keyword") String keyword);

    @Query("select p from Post p " +
            "where p.memberId in (" +
            "select m.memberId from Member m " +
            "where m.memberName like %:keyword%) " +
            "order by p.postId desc")
    List<Post> findByMemberNameKeyword(@Param("keyword") String keyword);

    @Query(value =
            "select " +
                    "post_id, " +
                    "topten.member_id," +
                    "title," +
                    "content," +
                    "tags," +
                    "created_time," +
                    "updated_time," +
                    "delete_flag " +
            "from (select * from Post p where p.delete_flag = 'N' order by p.created_time desc limit 10) topten " +
            "inner join Member m on topten.member_id = m.member_id " +
            "where topten.title = :title " +
            "and topten.member_id = :memberId "
            , nativeQuery = true)
    List<Post> findByMemberIdAndTitleWhereRowNumIn10(@Param("memberId") Long memberId, @Param("title") String title);
}
