package com.aqours_challenge.our_challenge.service;

import com.aqours_challenge.our_challenge.entity.Tag;
import com.aqours_challenge.our_challenge.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {
    private TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public String setTag(List<Tag> tags) {
        try {
            for (Tag tag : tags) {
                if (tagRepository.findByTagName(tag.getTagName()) != null) {
                    Tag result = tagRepository.save(tag);
                }
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
}
