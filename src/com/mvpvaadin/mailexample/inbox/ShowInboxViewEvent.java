package com.mvpvaadin.mailexample.inbox;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;

/**
 * This event is thrown to display the {@link InboxView}
 * @author Hannes Dorfmann
 *
 */
public class ShowInboxViewEvent extends Event<ShowInboxViewRequiredHandler>{

	private static final long serialVersionUID = 1343424346698621544L;

	public static final EventType<ShowInboxViewRequiredHandler> TYPE = new EventType<ShowInboxViewRequiredHandler>();
	
	@Override
	public EventType<ShowInboxViewRequiredHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowInboxViewRequiredHandler handler) {
		handler.onShowInboxViewRequired();
	}

}
