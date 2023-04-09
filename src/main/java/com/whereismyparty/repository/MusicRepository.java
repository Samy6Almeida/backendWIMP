package com.whereismyparty.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.whereismyparty.model.Music;

@Repository
public interface MusicRepository  extends PagingAndSortingRepository<Music, Long>, JpaSpecificationExecutor<Music> {
	
	Music findById(Long id);
	
}
