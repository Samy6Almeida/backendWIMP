package com.whereismyparty.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whereismyparty.model.Party;
import com.whereismyparty.model.User;

public interface UserService {
	User createUser(User user);
	User findOne(String username);
    User findId(Long id);
    Page<User> getAllUser(int page, int limit);
	void deleteUser(Long id);
	User updateUser(Long userId, User userDetails);
	
	  
}
