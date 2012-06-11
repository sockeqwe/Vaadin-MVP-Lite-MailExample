package com.mvpvaadin.mailexample.login;

import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.User;

public interface LoginSuccessfulHandler extends EventHandler {

	public void onLoginSuccessful(User authenticatedUser);
	
}
