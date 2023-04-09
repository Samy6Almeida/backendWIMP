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
import com.whereismyparty.model.User;
import com.whereismyparty.service.MusicService;

@RestController
@RequestMapping("/api")
public class MusicController {
	
	
	@Autowired
	MusicService musicService;
	
	
	//------------------- user registration -------------------
    @PostMapping("/music")
    
    ResponseEntity<?> createMusic(@RequestBody Music request){
    	Music response = musicService.createMusic(request);
    	 return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    
	//-------read the Party in json format with pagination----------
    @RequestMapping(value = "/music", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> getAllMusic(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {	    	
        Page<Music> response = musicService.getAllMusic(page, limit);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    //----------------------------------------------------------------------------------------------------
    
  //---------------------delete the Music ---------------------
    @DeleteMapping("/music/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable(value = "id") Long musicId) {
    	musicService.deleteMusic(musicId);
        return ResponseEntity.ok().build();
    }
    
  //----------------------------------------------------------------------------------------------------
    
    //--------------------update the User --------------------
    //Nao esquecer de atualizar o UserServiceImpl
    
    @RequestMapping(value = "/music/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateMusic(@PathVariable(value = "id") Long musicId, @RequestBody Music musicDetails) {
    Music response = musicService.updateMusic(musicId, musicDetails);
    return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    //----------------------------------------------------------------------
	

}
