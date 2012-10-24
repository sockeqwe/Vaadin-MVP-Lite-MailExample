package com.mvpvaadin.mailexample.writemail;

import com.mvplite.event.ShowViewEvent;

public class ShowWriteMailEvent extends ShowViewEvent {

	private static final long serialVersionUID = -8150281282476343956L;

	private String receiverMailAddress;
	private String subject;
	
	
	public ShowWriteMailEvent(){}
	public ShowWriteMailEvent(String receiverMailAddress, String subject){
		this.receiverMailAddress = receiverMailAddress;
		this.subject = subject;
	}
	
	public String getReceiverMailAddress() {
		return receiverMailAddress;
	}
	
	public String getSubject() {
		return subject;
	}
	
	


}
