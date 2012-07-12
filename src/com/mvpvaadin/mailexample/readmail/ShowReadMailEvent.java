package com.mvpvaadin.mailexample.readmail;

import com.mvplite.event.ShowViewEvent;
import com.mvplite.event.EventType;
import com.mvpvaadin.mailexample.data.Mail;

public class ShowReadMailEvent extends ShowViewEvent<ShowReadMailRequiredHandler>{

	public static final EventType<ShowReadMailRequiredHandler> TYPE = new EventType<ShowReadMailRequiredHandler>();
	
	private Mail mail;
	
	public ShowReadMailEvent(Mail mail){
		this.mail = mail;
	}
	
	@Override
	public EventType<ShowReadMailRequiredHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowReadMailRequiredHandler handler) {
		handler.onReadMailRequired(mail);
	}
	

}
