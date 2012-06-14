package com.mvpvaadin.mailexample.outbox;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;

public class ShowOutboxEvent extends Event<ShowOutboxHandler>{

	private static final long serialVersionUID = 2291299420081670227L;

	public static final EventType<ShowOutboxHandler> TYPE = 
			new EventType<ShowOutboxHandler>();
	
	@Override
	public EventType<ShowOutboxHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowOutboxHandler handler) {
		handler.onShowOutboxRequired();
	}

}
