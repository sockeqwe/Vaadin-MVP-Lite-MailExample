package com.mvpvaadin.mailexample.service.event;

import com.mvplite.event.EventHandler;

/**
 * The handler to handle {@link UnreadCountChangedEvent}s
 * @author hannes
 *
 */
public interface UnreadCountChangedHandler extends EventHandler{
	
	public void onUnreadCountChanged(int newUnreadValue);
}
