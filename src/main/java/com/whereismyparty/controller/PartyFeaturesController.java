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

import com.whereismyparty.model.PartyFeatures;
import com.whereismyparty.model.PartyKind;
import com.whereismyparty.service.PartyFeaturesService;

@RestController
@RequestMapping("/api")
public class PartyFeaturesController {
	
	
	@Autowired
	PartyFeaturesService partyFeaturesService;
	
	
	//------------------- user registration -------------------
    @PostMapping("/partyfeatures")
    
    ResponseEntity<?> createPartyFeatures(@RequestBody PartyFeatures request){
    	PartyFeatures response = partyFeaturesService.createPartyFeatures(request);
    	 return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
      
	//-------read the Party in json format with pagination----------
    @RequestMapping(value = "/partyfeatures", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> getAllPartyFeatures(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {	    	
        Page<PartyFeatures> response = partyFeaturesService.getAllPartyFeatures(page, limit);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    //----------------------------------------------------------------------------------------------------
    
    //---------------------delete the partyfeatures ---------------------
    @DeleteMapping("/partyfeatures/{id}")
    public ResponseEntity<?> deletePartyFeatures(@PathVariable(value = "id") Long partyfeaturesId) {
    	partyFeaturesService.deletePartyFeatures(partyfeaturesId);
        return ResponseEntity.ok().build();
    }
    
  //----------------------------------------------------------------------------------------------------
    
    //--------------------update the partyfeatures --------------------
    
    @RequestMapping(value = "/partyfeatures/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updatePartyFeatures(@PathVariable(value = "id") Long partyfeaturesId, @RequestBody PartyFeatures partyFeaturesDetails) {
    	PartyFeatures response = partyFeaturesService.updatePartyFeatures(partyfeaturesId, partyFeaturesDetails);
    return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    //----------------------------------------------------------------------
    
    
    
    

}
