package com.hummersoft.eira.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.model.User;
import com.hummersoft.eira.repository.UserRepository;


/**
 * @author 
 *
 */

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	 
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		//System.out.println("user present"+email);
		return userRepo.findActiveuserByEmail(email);
	}

}
