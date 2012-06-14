package com.mvpvaadin.mailexample.writemail;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;

public class ShowWriteMailEvent extends Event<ShowWriteMailHandler> {

	private static final long serialVersionUID = -8150281282476343956L;


	public static final EventType<ShowWriteMailHandler> TYPE =
			new EventType<ShowWriteMailHandler>();
	
	
	private String receiverMailAddress;
	private String subject;
	
	
	public ShowWriteMailEvent(){}
	public ShowWriteMailEvent(String receiverMailAddress, String subject){
		this.receiverMailAddress = receiverMailAddress;
		this.subject = subject;
	}
	
	
	@Override
	public EventType<ShowWriteMailHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowWriteMailHandler handler) {
		handler.onShowWriteMailViewRequired(receiverMailAddress, subject);
	}

}
