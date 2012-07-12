package com.mvpvaadin.mailexample.login;

import com.mvplite.event.Event;
import com.mvplite.event.EventType;
import com.mvpvaadin.mailexample.data.User;

public class LoginSuccessfulEvent extends Event<LoginSuccessfulHandler>{

	private static final long serialVersionUID = -1165578928902005502L;

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
