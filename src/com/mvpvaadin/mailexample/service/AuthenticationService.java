package com.mvpvaadin.mailexample.service;

import java.io.Serializable;

import com.mvpvaadin.mailexample.data.User;

/**
 * A simple Authentication service simulation, implemented as singleton. 
 * So use {@link #getInstance()} to access the singleton instance.
 * @author Hannes Dorfmann
 *
 */
public class AuthenticationService implements Serializable{
	
	private static final long serialVersionUID = 5498616709841126210L;
	
	private int userIdCounter;
	
	public AuthenticationService(){
		userIdCounter = 0;
	}
	

	
	
	/**
	 * Simulate a login
	 * @param username
	 * @param password
	 * @return A valid {@link User} object or <code>null</code>
	 */
	public User doLogin(String username, String password){
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty())
			return null;
		
		return new User(++userIdCounter, username);
	}
	
	
	/**
	 * Simulate a logout
	 * @param user
	 */
	public void doLogout(User user){
		// simulate a logout
		
	}

}
