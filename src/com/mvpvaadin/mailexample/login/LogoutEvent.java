package com.mvpvaadin.mailexample.login;

import com.mvplite.event.Event;
import com.mvplite.event.EventType;
import com.mvpvaadin.mailexample.data.User;

public class LogoutEvent extends Event<LogoutHandler> {
	
	private static final long serialVersionUID = 1518609467415028320L;

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
