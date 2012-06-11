package com.mvpvaadin.mailexample.login;

import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.User;

public interface LogoutHandler extends EventHandler {

	public void onLogout(User user);
}
