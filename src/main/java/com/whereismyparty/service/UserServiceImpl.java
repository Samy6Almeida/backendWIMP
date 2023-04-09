package com.whereismyparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.whereismyparty.exception.DuplicateUserNameException;
import com.whereismyparty.exception.ResourceNotFoundException;
import com.whereismyparty.model.Party;
import com.whereismyparty.model.Story;
import com.whereismyparty.model.User;
import com.whereismyparty.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    //---------------------------------------------------------------------

    @Autowired
    private UserRepository userRepository;
    //---------------------------------------------------------------------

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());

    }
    //---------------------------------------------------------------------


    private List<SimpleGrantedAuthority> getAuthority() {
    	//NUNO
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    	return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
    //---------------------------------------------------------------------

    @Override
    public User findOne(String username) {
        return userRepository.findByUserName(username);
    }
    //---------------------------------------------------------------------

    @Override
    public User createUser(User user) {
        if(user.getUserName() == null)
            throw new ResourceNotFoundException("Username is required", "Username", user.getUserName());

        if(user.getPassword() == null)
            throw new ResourceNotFoundException("Password is required", "Password", user.getPassword());
        
        if(user.getName() == null)
            throw new ResourceNotFoundException("User Name is required", "Name", user.getName());

        User usr = userRepository.findByUserName(user.getUserName());
        if(usr != null)
            throw new DuplicateUserNameException("Username Already exists.");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    //---------------------------------------------------------------------
    
    //---------------------------------------------------------------------

	@Override
	@RolesAllowed({"", ""})
	public void deleteUser(Long id) {
		
        User user = userRepository.findOne(id);
		 if(user == null)
	            throw new ResourceNotFoundException("User ID", "id", id);		 
		 userRepository.delete(id);;
	}
	
    //---------------------------------------------------------------------

    @Override
    public Page<User> getAllUser(int page, int limit) {
        Page<User> resultPage = userRepository.findAll(new PageRequest(page,limit));
        if (page > resultPage.getTotalPages())
             throw new ResourceNotFoundException("User", "id", resultPage);
        return resultPage;
    }
    //---------------------------------------------------------------------


	@Override
	public User updateUser(Long userId, User userDetails) {
		User user = userRepository.findOne(userId);
		if(user == null)
            throw new ResourceNotFoundException("User", "id", user);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setName(userDetails.getName());
		user.setUserName(userDetails.getUserName());
		user.setPassword(encoder.encode(userDetails.getPassword()));
		user.setBirth_date(userDetails.getBirth_date());
		user.setSex(userDetails.getSex());
        return userRepository.save(user);
		
	}
    //---------------------------------------------------------------------


	@Override
	public User findId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

    //---------------------------------------------------------------------


    
    
//---------------------------end-------------------------------------------
}
