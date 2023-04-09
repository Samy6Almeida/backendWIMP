package com.whereismyparty.controller;



import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyparty.config.JwtTokenUtil;
import com.whereismyparty.model.Music;
import com.whereismyparty.model.Party;
import com.whereismyparty.repository.PartyRepository;
import com.whereismyparty.service.PartyService;
import com.whereismyparty.service.UserService;

@RestController
@RequestMapping("/api")

public class PartyController {
	
	    @Autowired
	    PartyService partyService;
	    
	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    JwtTokenUtil jwtTokenUtil ;
	 
	    @Autowired
	    private PartyRepository party;
	   
  
	   //============================================================================================================= 
	    @PostMapping("/party")
	    ResponseEntity<?> createParty(@RequestBody Party request, Principal principal) {
	    	
	    	// Para esta API a proxima linha significa que vai usar o Usuario logado para criar a festa
	    	// sem a necessidade de informar o userID
	    	request.setUser(getUserByUsername(principal.getName()));
	    	
	    	System.out.print("getUserByUsername(principal.getName()==>"+getUserByUsername(principal.getName()).getName()+"\n");
	    	
	    	Party response = partyService.createParty(request);
	        return new ResponseEntity<Object>(response, HttpStatus.OK);
	               
	    }
	    
	  //=============================================================================================================

	//-------read the Party in json format with pagination----------
	    @RequestMapping(value = "/party", method = RequestMethod.GET, produces = {"application/json"})
	    ResponseEntity<?> getAllParty(@RequestParam(value = "page", defaultValue = "0") int page,
	                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {	    	
	        Page<Party> response = partyService.getAllParty(page, limit);
	        return new ResponseEntity<Object>(response, HttpStatus.OK);
	    }	
	    
	  //-------Ler a festa em formato de json pelo id da music----------
	    @GetMapping("/parties/music/{musicId}")
	    public List<Party> getPartiesByMusicId(@PathVariable Long musicId) {
	        return party.findByMusicId(musicId);
	    }
	    
	  //-------Ler a festa em formato de json pelo id da partyFeatures----------
	    @GetMapping("/parties/party-features/{partyFeaturesId}")
	    public List<Party> getPartiesByPartyFeaturesId(@PathVariable Long partyFeaturesId) {
	        return party.findByPartyFeaturesId(partyFeaturesId);
	    }
	    
	  //-------Ler a festa em formato de json pelo id da kind----------
	    @GetMapping("/parties/byKind/{kindId}")
	    public List<Party> getPartiesByKindId(@PathVariable Long kindId) {
	        return party.findByKindId(kindId);
	    }
   
	    //--------------------update the Party --------------------
	    @RequestMapping(value = "/party/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	    public ResponseEntity<?> updateParty(@PathVariable(value = "id") Long partyId, @RequestBody Party partyDetails, Principal principal) {
	    	System.out.print("principal.getName()==>"+principal.getName()+"\n");
	    	
	    	partyDetails.setUser(getUserByUsername(principal.getName()));
	        Party response =  partyService.updateParty(partyId, partyDetails);
	        return new ResponseEntity<Object>(response, HttpStatus.OK);
	    }
	    


		//---------------------delete the party ---------------------
	    @DeleteMapping("/party/{id}")
	    public ResponseEntity<?> deleteParty(@PathVariable(value = "id") Long partyId) {
	    	partyService.deleteParty(partyId);
	        return ResponseEntity.ok().build();
	    }
	    
    
	
	//============================================================
  		public com.whereismyparty.model.User getUserByUsername(String username) 
  		{
  			return getUserService().findOne(username);
  		}
	 //============================================================
	
		public UserService getUserService() 
		{
			return userService;
		}
	//============================================================
		
	
		
	    
	    
}
