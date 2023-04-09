package com.whereismyparty.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.whereismyparty.model.PartyFeatures;

@Repository
public interface PartyFeaturesRepository extends PagingAndSortingRepository<PartyFeatures, Long>, JpaSpecificationExecutor<PartyFeatures> {

	PartyFeatures findById(Long id);
	
}
