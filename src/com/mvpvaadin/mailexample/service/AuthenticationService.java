package com.mvpvaadin.mailexample.service;

import com.mvpvaadin.mailexample.data.User;

/**
 * A simple Authentication service simulation, implemented as singleton. 
 * So use {@link #getInstance()} to access the singleton instance.
 * @author Hannes Dorfmann
 *
 */
public class AuthenticationService {
	
	private static AuthenticationService INSTANCE = new AuthenticationService();
	
	private int userIdCounter;
	
	private AuthenticationService(){
		userIdCounter = 0;
	}
	
	
	public static AuthenticationService getInstance(){
		return INSTANCE;
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
