package com.mvpvaadin.mailexample.service.event;

import com.mvplite.event.Event;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;


/**
 * This event informs all corresponding handlers, that the number of unread {@link Mail}s of the 
 * authenticated {@link User} has changed.
 * @author Hannes Dorfmann
 *
 */
public class UnreadCountChangedEvent extends Event {

	private static final long serialVersionUID = -2557815297461590953L;
	private final int newUnradCount;
	
	public UnreadCountChangedEvent(int newUnreadCount){
		this.newUnradCount = newUnreadCount;
	}
	
	public int getUnreadCount(){
		return newUnradCount;
	}

}
