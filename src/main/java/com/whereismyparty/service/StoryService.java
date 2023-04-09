package com.whereismyparty.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.whereismyparty.model.Story;

import java.util.List;

public interface StoryService {
    Story createStory(Story story);
    Page<Story> getAllStory(int page, int limit);
    Story updateStory(Long id, Story storyDetails);
    void deleteStory(Long id);
    List<Story> searchStory(Specification<Story> spec);
}
