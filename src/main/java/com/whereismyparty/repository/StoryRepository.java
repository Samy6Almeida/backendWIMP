package com.whereismyparty.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.whereismyparty.model.Story;

@Repository
public interface StoryRepository extends PagingAndSortingRepository<Story, Long>, JpaSpecificationExecutor<Story> {
    Story findById(Long id);
}
