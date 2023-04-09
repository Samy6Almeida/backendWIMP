package com.whereismyparty.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;

import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyKind;

public interface PartyService {
	
	Party createParty(Party party);
    Page<Party> getAllParty(int page, int limit);;
   
    void deleteParty(Long id);
    List<Party> searchParty(Specification<Party> spec);
    
    Party updateParty(Long id, Party partyDetails);
	//Party updateParty(Long id, Party partyDetails, Principal principal);
    
}


