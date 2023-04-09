package com.whereismyparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyparty.model.Music;
import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyKind;
import com.whereismyparty.service.PartyKindService;


@RestController
@RequestMapping("/api")
public class PartyKindController {
	
	@Autowired
	PartyKindService partyKindService;
	

	//------------------- user registration -------------------
    @PostMapping("/partykind")
    
    ResponseEntity<?> createPartyKind(@RequestBody PartyKind request){
    	PartyKind response = partyKindService.createPartyKind(request);
    	 return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    
	//-------read the Party in json format with pagination----------
    @RequestMapping(value = "/partykind", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> getAllPartyKind(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {	    	
        Page<PartyKind> response = partyKindService.getAllPartyKind(page, limit);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    //----------------------------------------------------------------------------------------------------
    
  //---------------------delete the partykind ---------------------
    @DeleteMapping("/partykind/{id}")
    public ResponseEntity<?> deletePartyKind(@PathVariable(value = "id") Long partykindId) {
    	partyKindService.deletePartyKind(partykindId);
        return ResponseEntity.ok().build();
    }
    
  //----------------------------------------------------------------------------------------------------
    
    //--------------------update the partykind --------------------
    
    @RequestMapping(value = "/partykind/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updatePartyKind(@PathVariable(value = "id") Long partykindId, @RequestBody PartyKind partykindDetails) {
	PartyKind response = partyKindService.updatePartyKind(partykindId, partykindDetails);
    return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    //----------------------------------------------------------------------
    
    
    
    
}

