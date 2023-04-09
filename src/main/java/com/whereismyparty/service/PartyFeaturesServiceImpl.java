package com.whereismyparty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.whereismyparty.exception.ResourceNotFoundException;
import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyFeatures;
import com.whereismyparty.model.PartyKind;
import com.whereismyparty.repository.PartyFeaturesRepository;
import com.whereismyparty.repository.PartyRepository;

@Service
public class PartyFeaturesServiceImpl implements PartyFeaturesService  {
	
	  
	@Autowired
	PartyFeaturesRepository partyFeaturesRepository;
	
    //---------------------------------------------------------------------

	@Override
	public PartyFeatures createPartyFeatures(PartyFeatures partyFeatures) {
		   return partyFeaturesRepository.save(partyFeatures);
	}
    //---------------------------------------------------------------------

	@Override
	public Page<PartyFeatures> getAllPartyFeatures(int page, int limit) {
		Page<PartyFeatures> resultPage = partyFeaturesRepository.findAll(new PageRequest(page,limit));
        if (page > resultPage.getTotalPages())
             throw new ResourceNotFoundException("PartyFeatures", "id", resultPage);
        return resultPage;
	}
    //---------------------------------------------------------------------

	@Override
	public PartyFeatures updatePartyFeatures(Long id, PartyFeatures partyFeatures) {
		PartyFeatures partyfeaturesupdate = partyFeaturesRepository.findById(id);
        if(partyfeaturesupdate == null)
            throw new ResourceNotFoundException("PartyFeatures", "id", id);
        partyfeaturesupdate.setKind(partyFeatures.getKind());
        return partyFeaturesRepository.save(partyfeaturesupdate);
	}
    //---------------------------------------------------------------------

	@Override
	public void deletePartyFeatures(Long id) {
		PartyFeatures partyfeatures = partyFeaturesRepository.findById(id);
        if(partyfeatures == null)
            throw new ResourceNotFoundException("PartyFeaturesID", "id", id);
        partyFeaturesRepository.delete(partyfeatures);	
		
	}


}
