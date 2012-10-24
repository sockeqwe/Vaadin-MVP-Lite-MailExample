package com.mvpvaadin.mailexample.service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mvpvaadin.mailexample.data.User;

/**
 * A simple Authentication service simulation, implemented as singleton. 
 * So use {@link #getInstance()} to access the singleton instance.
 * @author Hannes Dorfmann
 *
 */
public class AuthenticationService implements Serializable{
	
	private static final long serialVersionUID = 5498616709841126210L;
	
	private static final AuthenticationService INSTANCE = new AuthenticationService();
	
	private int userIdCounter;
	private final Map<String, User> userMap;
	
	
	private AuthenticationService(){
		userIdCounter = 0;
		userMap = new ConcurrentHashMap<String, User>();
	}
	
	public static AuthenticationService getInstance(){
		return INSTANCE;
	}
	
	public User isUser(String email){
		
		String parts[] = email.split("@");
		
		if (parts == null || parts.length !=2 || !parts[1].equals(MailService.MAIL_DOMAIN))
			return null;
		
		return userMap.get(parts[0]);
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
		
		User u = new User(++userIdCounter, username);
		userMap.put(username, u);
		return u;
	}
	
	
	/**
	 * Simulate a logout by deleting all mails
	 * @param user
	 */
	public void doLogout(User user){
		MailService.getInstance().deleteAllMailsOf(user);
		userMap.remove(user.getUsername());
		
	}

}
