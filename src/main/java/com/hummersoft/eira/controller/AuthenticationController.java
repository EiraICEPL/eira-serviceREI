package com.hummersoft.eira.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hummersoft.eira.dao.JwtRequest;
import com.hummersoft.eira.model.User;
import com.hummersoft.eira.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
	
	//private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private UserService userService;
		   
	   
	   @PostMapping(value = "/login")
		public ResponseEntity<User> isValidUser(@RequestBody JwtRequest authenticationRequest){
			
			//logger.info("Request for loginUser>>{}", authenticationRequest.getEmail());
			try {
				Optional<User>  userData = userService.getUserByEmail(authenticationRequest.getEmail());
				
				//System.out.println(userData.isPresent());
				if (userData.isPresent()){
					//System.out.println("user present");
					if (userData.get().getPassword().equals(authenticationRequest.getPassword())) {
						//System.out.println("same passowrd");
						return new ResponseEntity<>(userData.get(),HttpStatus.OK);
					}else
						return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
				}else 
					return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
				
			}catch (Exception e) {
				e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
		}



}

