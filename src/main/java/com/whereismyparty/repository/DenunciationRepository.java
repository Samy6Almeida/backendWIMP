package com.whereismyparty.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.whereismyparty.model.Denunciation;

@Repository
public interface DenunciationRepository extends PagingAndSortingRepository<Denunciation, Long>, JpaSpecificationExecutor<Denunciation> {
	Denunciation findById(Long id);
}
