package com.mvpvaadin.mailexample.service.event;

import com.mvplite.event.Event;
import com.mvplite.event.EventType;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;


/**
 * This event informs all {@link UnreadCountChangedHandler}s, that the number of unread {@link Mail}s of the 
 * authenticated {@link User} has changed.
 * @author Hannes Dorfmann
 *
 */
public class UnreadCountChangedEvent extends Event<UnreadCountChangedHandler> {

	public static final EventType<UnreadCountChangedHandler> TYPE = new EventType<UnreadCountChangedHandler>();
	
	private int newUnradCount;
	
	public UnreadCountChangedEvent(int newUnreadCount){
		this.newUnradCount = newUnreadCount;
	}
	
	
	@Override
	public EventType<UnreadCountChangedHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(UnreadCountChangedHandler handler) {
		handler.onUnreadCountChanged(newUnradCount);
	}

}
