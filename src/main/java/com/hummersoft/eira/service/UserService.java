package com.hummersoft.eira.service;

import java.util.Optional;

import com.hummersoft.eira.model.User;

/**
 * @author 
 *
 */

public interface UserService {

	 //public User getUserByNameAndPassword(String name, String password); 
	 
	 public Optional<User>  getUserByEmail(String email);
}