package com.mvpvaadin.mailexample.inbox;

import com.mvplite.event.EventType;
import com.mvplite.event.ShowViewEvent;

/**
 * This event is thrown to display the {@link InboxView}
 * @author Hannes Dorfmann
 *
 */
public class ShowInboxViewEvent extends ShowViewEvent<ShowInboxViewRequiredHandler>{

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
