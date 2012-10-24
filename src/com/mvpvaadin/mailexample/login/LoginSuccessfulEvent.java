package com.mvpvaadin.mailexample.login;

import com.mvplite.event.Event;
import com.mvpvaadin.mailexample.data.User;

public class LoginSuccessfulEvent extends Event{

	private static final long serialVersionUID = -1165578928902005502L;

	
	private final User authenticatedUser;
	
	
	public LoginSuccessfulEvent(User authenticatedUser){
		this.authenticatedUser = authenticatedUser;
	}
	
	

	public User getUser(){
		return authenticatedUser;
	}
}
