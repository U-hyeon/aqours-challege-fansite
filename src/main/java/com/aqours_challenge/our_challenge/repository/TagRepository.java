package com.aqours_challenge.our_challenge.repository;

import com.aqours_challenge.our_challenge.entity.Tag;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Table(name = "Tag")
public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByTagName(String TagName);
}
