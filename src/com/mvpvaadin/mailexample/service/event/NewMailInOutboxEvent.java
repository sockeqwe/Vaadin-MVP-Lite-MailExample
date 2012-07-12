package com.mvpvaadin.mailexample.service.event;

import com.mvplite.event.Event;
import com.mvplite.event.EventType;
import com.mvpvaadin.mailexample.data.Mail;

public class NewMailInOutboxEvent extends Event<NewMailInOutboxHandler>{
	
	private static final long serialVersionUID = -8471872653379633445L;


	public static EventType<NewMailInOutboxHandler> TYPE =
			new EventType<NewMailInOutboxHandler>();
	
	
	private Mail mail;
	
	public NewMailInOutboxEvent(Mail newMail){
		this.mail = newMail;
	}
	
	
	@Override
	public EventType<NewMailInOutboxHandler> getType() {
		return TYPE;
	}


	@Override
	public void dispatch(NewMailInOutboxHandler handler) {
		handler.onNewMailInOutbox(mail);
	}

}
