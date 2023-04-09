package com.whereismyparty.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyFeatures;


public interface PartyFeaturesService {
	
	
	PartyFeatures createPartyFeatures(PartyFeatures partyFeatures);

	Page<PartyFeatures> getAllPartyFeatures(int page, int limit);

	PartyFeatures updatePartyFeatures(Long id, PartyFeatures partyFeatures);
	
	void deletePartyFeatures(Long id);

}
