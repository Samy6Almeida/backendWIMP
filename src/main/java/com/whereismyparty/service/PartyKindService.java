package com.whereismyparty.service;

import org.springframework.data.domain.Page;

import com.whereismyparty.model.PartyKind;

public interface PartyKindService {

	PartyKind createPartyKind(PartyKind partyKind);

	Page<PartyKind> getAllPartyKind(int page, int limit);

	PartyKind updatePartyKind(Long id, PartyKind partyDetails);

	void deletePartyKind(Long id);

	//Page<PartyKind> getAllParty(int page, int limit);
}
