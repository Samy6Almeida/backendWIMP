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

import com.whereismyparty.model.User;
import com.whereismyparty.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	//----------------------------------
    @Autowired
    UserService userService; //---Use este para referenciar abaixo.

    //---------------------------------------------------------
    @PostMapping("/userwithoutjwt")
    ResponseEntity<?> createUser2(@RequestBody User request) {
        User response = userService.createUser(request);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    //------------------- user registration -------------------
    @PostMapping("/user")
    ResponseEntity<?> createUser(@RequestBody User request) {
        User response = userService.createUser(request);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    
    //-------read the User in json format with pagination----------
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<?> getAllUser(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {
        Page<User> response = userService.getAllUser(page, limit);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    
    
  //---------------------delete the User ---------------------
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
    	userService.deleteUser(id);  // Faz referencia com a UserServiceImpl.java
        return ResponseEntity.ok().build();
    }
    

    //--------------------update the User --------------------
    //Nao esquecer de atualizar o UserServiceImpl
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {

        User response = userService.updateUser(userId, userDetails);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    //----------------------------------------------------------------------
    
  //--------------------update the User --------------------
    //Nao esquecer de atualizar o UserServiceImpl
    
    @RequestMapping(value = "/userupdate/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateUser2(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {

        User response = userService.updateUser(userId, userDetails);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    //----------------------------------------------------------------------
    
    

    
    //============================================================
      		public com.whereismyparty.model.User getUserByUsername(String username) 
      		{
      			return getUserService().findOne(username);
      		}
      		
      		
      		public UserService getUserService() 
    		{
    			return userService;
    		}
    //----------------------------------------------------------------------

    
//-----------------------------end--------------------------------------   
}
