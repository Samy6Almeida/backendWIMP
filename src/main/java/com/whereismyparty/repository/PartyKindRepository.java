package com.whereismyparty.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.whereismyparty.model.PartyKind;

@Repository
public interface PartyKindRepository extends PagingAndSortingRepository<PartyKind, Long>, JpaSpecificationExecutor<PartyKind> {

	PartyKind findById(Long id);


}
