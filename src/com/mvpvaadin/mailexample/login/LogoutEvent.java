package com.mvpvaadin.mailexample.login;

import com.mvplite.event.Event;
import com.mvpvaadin.mailexample.data.User;

public class LogoutEvent extends Event {
	
	private static final long serialVersionUID = 1518609467415028320L;

	private final User user;
	
	
	public LogoutEvent(User user){
		this.user = user;
	}

	
	public User getUser(){
		return user;
	}

}
