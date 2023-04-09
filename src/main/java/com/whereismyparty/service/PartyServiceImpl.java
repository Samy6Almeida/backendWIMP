package com.whereismyparty.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.whereismyparty.config.JwtTokenUtil;
import com.whereismyparty.exception.ResourceNotFoundException;
import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyKind;
import com.whereismyparty.repository.PartyRepository;

@Service
public class PartyServiceImpl implements PartyService {

	
	
	  //---------------------------------------------------------------------
    @Autowired
    PartyRepository PartyRepository;
    
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    //---------------------------------------------------------------------

    @Override
    public Party createParty(Party party) {
        return PartyRepository.save(party);
    }
    //---------------------------------------------------------------------
	
			@Override
			public Page<Party> getAllParty(int page, int limit) {
				Page<Party> resultPage = PartyRepository.findAll(new PageRequest(page,limit));
			        if (page > resultPage.getTotalPages())
			             throw new ResourceNotFoundException("Party", "id", resultPage);
			        return resultPage;
			}

    //---------------------------------------------------------------------


	@Override
	public Party updateParty(Long id, Party partyDetails) {
		Party party = PartyRepository.findById(id);
	        if(party == null)
	            throw new ResourceNotFoundException("Party", "id", id);
	       party.setParty_start_date(partyDetails.getParty_start_date());
	       party.setParty_ends_date(partyDetails.getParty_ends_date());
	       party.setPublication_date(partyDetails.getPublication_date());
	       party.setDescription(partyDetails.getDescription());
	        party.setInstagram_link(partyDetails.getInstagram_link());
	        party.setTitle(partyDetails.getTitle());
	        party.setUser(partyDetails.getUser());;
	        party.setKinds(partyDetails.getKinds());
	        party.setLatitude(partyDetails.getLatitude());
	        party.setLongitude(partyDetails.getLongitude());
	        party.setPartyFeatures(partyDetails.getPartyFeatures());
	        party.setMusic(partyDetails.getMusic());
	        party.setAddress(partyDetails.getAddress());
	        
	        return PartyRepository.save(party);


	}
	/*
			@Override
			public Party updateParty(Long id, Party partyDetails) {
				// TODO Auto-generated method stub
				return null;
			}
    */
	
    //---------------------------------------------------------------------


	@Override
	public void deleteParty(Long id) {
		Party party = PartyRepository.findById(id);
		//Party party = PartyRepository.findByUserId(id);
	        if(party == null)
	            throw new ResourceNotFoundException("Party", "id", id);

	        PartyRepository.delete(party);		
	}
    //---------------------------------------------------------------------

	@Override
	public List<Party> searchParty(Specification<Party> spec) {
	    return PartyRepository.findAll(spec);
	}








	
}
