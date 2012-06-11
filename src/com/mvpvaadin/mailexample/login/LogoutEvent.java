package com.mvpvaadin.mailexample.login;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;
import com.mvpvaadin.mailexample.data.User;

public class LogoutEvent extends Event<LogoutHandler> {
	
	private User user;
	
	public static final EventType<LogoutHandler> TYPE = new EventType<LogoutHandler>();
	
	public LogoutEvent(User user){
		this.user = user;
	}

	@Override
	public void dispatch(LogoutHandler handler) {
		handler.onLogout(user);
	}

	@Override
	public EventType<LogoutHandler> getType() {
		return TYPE;
	}

}
