package com.aqours_challenge.our_challenge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tag_Post")
public class TagPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TagPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Long getTagPostId() {
        return TagPostId;
    }

    public void setTagPostId(Long tagPostId) {
        TagPostId = tagPostId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public static TagPost createTagPost(Tag tag, Post post) {
        TagPost tagPost = new TagPost();
        tagPost.setTag(tag);
        tagPost.setPost(post);
        return tagPost;
    }
}
