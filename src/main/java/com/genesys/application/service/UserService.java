package com.genesys.application.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.application.exception.UserNotFoundException;
import com.genesys.application.model.User;
import com.genesys.application.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	public void deleteAllUsers () {
		this.userRepository.deleteAll();
	}
	
	
	/**
	 * @param user
	 * @return String - the user id
	 * 
	 * we only care about the result of user saving, there is no need return the whole user object
	 * the user id will help API consumer target the user if it is necessary
	 */
	public String saveUser (User user) {
		return this.userRepository.save(user).getId();
	}
	
	
	
	/**
	 * @param userId
	 * @return the existing user or throw UserNotFoundException if not found
	 */
	public User findUserById (String userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
	}
	
	public List<User> findAllUsers () {
		return this.userRepository.findAll();
	}
}
