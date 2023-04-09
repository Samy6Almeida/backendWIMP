package com.whereismyparty.controller;

import java.security.Principal;

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

import com.whereismyparty.model.Denunciation;
import com.whereismyparty.service.DenunciationService;
import com.whereismyparty.service.UserService;

@RestController
@RequestMapping("/api")
public class DenunciatonController {
	
    @Autowired
    UserService userService;
	
	 @Autowired
	 DenunciationService denunciationService;
	 
	   //------------------- user registration -------------------
	    @PostMapping("/denunciation")
	    ResponseEntity<?> createDenunciation(@RequestBody Denunciation request, Principal principal) {
	    	
	    	// Para esta API a proxima linha significa que vai usar o Usuario logado para criar a festa
	    	// sem a necessidade de informar o userID
	    	request.setUser(getUserByUsername(principal.getName()));

	    	
	    	Denunciation response = denunciationService.createDenunciation(request);
	        return new ResponseEntity<Object>(response, HttpStatus.OK);
	    }
	    
	    
	    //-------read the Denunciation in json format with pagination----------
	    @RequestMapping(value = "/denunciation", method = RequestMethod.GET, produces = {"application/json"})
	    ResponseEntity<?> getAllDenunciation(@RequestParam(value = "page", defaultValue = "0") int page,
	                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {
	        Page<Denunciation> response = denunciationService.getAllDenunciation(page, limit);
	        return new ResponseEntity<Object>(response, HttpStatus.OK);
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
	    //---------------------delete the story ---------------------
	    @DeleteMapping("/denunciation/{id}")
	    public ResponseEntity<?> deleteDenunciation(@PathVariable(value = "id") Long denunciationId) {
	    	denunciationService.deleteDenunciation(denunciationId);
	        return ResponseEntity.ok().build();
	    }

	 
	 
	}
