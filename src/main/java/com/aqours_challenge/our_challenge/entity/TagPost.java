package com.aqours_challenge.our_challenge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tag_Post")
public class TagPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TagPostId;

    @JoinColumn(name = "tag_id")
    private Long tagId;

    @JoinColumn(name = "post_id")
    private Long postId;

    public Long getTagPostId() {
        return TagPostId;
    }

    public void setTagPostId(Long tagPostId) {
        TagPostId = tagPostId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public static TagPost createTagPost(Long tagId, Long postId) {
        TagPost tagPost = new TagPost();
        tagPost.setTagId(tagId);
        tagPost.setPostId(postId);
        return tagPost;
    }
}
