package com.mvpvaadin.mailexample.main;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;

/**
 * This event is fired, to show the {@link MainView} in its initial state
 * @author Hannes Dorfmann
 *
 */
public class ShowMainViewEvent extends Event<ShowMainViewHandler>{

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
