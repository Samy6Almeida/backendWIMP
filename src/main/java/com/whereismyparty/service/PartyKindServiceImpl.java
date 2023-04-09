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
import com.whereismyparty.model.Music;
import com.whereismyparty.model.PartyKind;
import com.whereismyparty.repository.PartyKindRepository;

@Service
public class PartyKindServiceImpl implements PartyKindService {

	
	
	  //---------------------------------------------------------------------
    @Autowired
    PartyKindRepository partyKindRepository;

    //---------------------------------------------------------------------

    @Override
    public PartyKind createPartyKind(PartyKind partyKind) {
        return partyKindRepository.save(partyKind);
    }
    //---------------------------------------------------------------------
	
			@Override
			public Page<PartyKind> getAllPartyKind(int page, int limit) {
				Page<PartyKind> resultPage = partyKindRepository.findAll(new PageRequest(page,limit));
			        if (page > resultPage.getTotalPages())
			             throw new ResourceNotFoundException("PartyKind", "id", resultPage);
			        return resultPage;
			}
		    //---------------------------------------------------------------------

			@Override
			public PartyKind updatePartyKind(Long id, PartyKind partyDetails) {
				PartyKind partykindupdate = partyKindRepository.findById(id);
		        if(partykindupdate == null)
		            throw new ResourceNotFoundException("PartyKind", "id", id);
		        partykindupdate.setKind(partyDetails.getKind());
		        return partyKindRepository.save(partykindupdate);
			}

			//---------------------------------------------------------------------
			@Override
			public void deletePartyKind(Long id) {
				PartyKind partykind = partyKindRepository.findById(id);
			        if(partykind == null)
			            throw new ResourceNotFoundException("MuPartyKindsic", "id", id);
			        partyKindRepository.delete(partykind);		
			}


    //---------------------------------------------------------------------


    //---------------------------------------------------------------------







	
}
