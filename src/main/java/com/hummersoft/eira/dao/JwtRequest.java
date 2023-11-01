package com.hummersoft.eira.dao;

import java.io.Serializable;
/**
 * 
 * @author 
 *
 */
public class JwtRequest implements Serializable{
	
private static final long serialVersionUID = 5926468583005150707L;
	
	private String userName;
	private String password;
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}



	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

