package com.mvpvaadin.mailexample.outboxmaildetails;

import com.mvplite.event.EventType;
import com.mvplite.event.ShowViewEvent;
import com.mvpvaadin.mailexample.data.Mail;

public class ShowOutboxMailDetailsEvent extends ShowViewEvent<ShowOutboxMailDetailsHandler>{
	
	private static final long serialVersionUID = -3588415708925566297L;
	
	private Mail mail;
	
	public static EventType<ShowOutboxMailDetailsHandler> TYPE =
			new EventType<ShowOutboxMailDetailsHandler>();
	
	
	public ShowOutboxMailDetailsEvent(Mail mailToShow){
		this.mail = mailToShow;
	}
	

	@Override
	public EventType<ShowOutboxMailDetailsHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowOutboxMailDetailsHandler handler) {
		handler.onShowOutboxMailDetailsRequired(mail);
	}

}
