package com.mvpvaadin.mailexample.login;

import com.mvplite.event.EventHandler;
import com.mvpvaadin.mailexample.data.User;

public interface LogoutHandler extends EventHandler {

	public void onLogout(User user);
}
