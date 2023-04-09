package com.whereismyparty.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.whereismyparty.model.Denunciation;
import com.whereismyparty.model.Party;

public interface DenunciationService {
	
	Denunciation createDenunciation(Denunciation denunciation);
    Page<Denunciation> getAllDenunciation(int page, int limit);
    Party updateDenunciation(Long id, Party DenunciationDetails);
    void deleteDenunciation(Long id);
    List<Denunciation> searchDenunciation(Specification<Denunciation> spec);
	

}
