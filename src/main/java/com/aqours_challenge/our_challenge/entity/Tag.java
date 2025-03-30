package com.aqours_challenge.our_challenge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;

    @Column(name="tag_name", unique=true)
    private String tagName;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public static Tag createTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        return tag;
    }
}
