package com.mvpvaadin.mailexample.main;

import com.mvplite.event.EventType;
import com.mvplite.event.ShowViewEvent;

/**
 * This event is fired, to show the {@link MainView} in its initial state
 * @author Hannes Dorfmann
 *
 */
public class ShowMainViewEvent extends ShowViewEvent<ShowMainViewHandler>{

	private static final long serialVersionUID = -2440487033349412385L;
	
	
	public static final EventType<ShowMainViewHandler> TYPE = new EventType<ShowMainViewHandler>();
	
	@Override
	public EventType<ShowMainViewHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowMainViewHandler handler) {
		handler.onShowMainViewRequired();
	}

}
