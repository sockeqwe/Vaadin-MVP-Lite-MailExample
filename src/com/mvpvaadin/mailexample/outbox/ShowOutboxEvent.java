package com.mvpvaadin.mailexample.outbox;

import com.mvplite.event.EventType;
import com.mvplite.event.ShowViewEvent;
import com.mvpvaadin.mailexample.data.Mail;

public class ShowOutboxEvent extends ShowViewEvent<ShowOutboxHandler>{

	private static final long serialVersionUID = 2291299420081670227L;

	public static final EventType<ShowOutboxHandler> TYPE = 
			new EventType<ShowOutboxHandler>();
	
	
	private Mail preselectedMail;
	
	public ShowOutboxEvent(Mail preselectedMail){
		this.preselectedMail = preselectedMail;
	}
	
	
	@Override
	public EventType<ShowOutboxHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowOutboxHandler handler) {
		handler.onShowOutboxRequired(preselectedMail);
	}

}
