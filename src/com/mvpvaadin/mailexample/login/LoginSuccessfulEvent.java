package com.mvpvaadin.mailexample.login;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;
import com.mvpvaadin.mailexample.data.User;

public class LoginSuccessfulEvent extends Event<LoginSuccessfulHandler>{

	public static EventType<LoginSuccessfulHandler> TYPE = new EventType<LoginSuccessfulHandler>();
	
	private User authenticatedUser;
	
	
	public LoginSuccessfulEvent(User authenticatedUser){
		this.authenticatedUser = authenticatedUser;
	}
	
	
	@Override
	public void dispatch(LoginSuccessfulHandler handler) {
		handler.onLoginSuccessful(authenticatedUser);
	}


	@Override
	public EventType<LoginSuccessfulHandler> getType() {
		return TYPE;
	}

}
