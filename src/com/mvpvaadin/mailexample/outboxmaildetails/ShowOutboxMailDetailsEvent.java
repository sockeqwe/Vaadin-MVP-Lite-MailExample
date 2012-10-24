package com.mvpvaadin.mailexample.outboxmaildetails;

import com.mvplite.event.ShowViewEvent;
import com.mvpvaadin.mailexample.data.Mail;

public class ShowOutboxMailDetailsEvent extends ShowViewEvent{
	
	private static final long serialVersionUID = -3588415708925566297L;
	
	private final Mail mail;
	
	
	
	public ShowOutboxMailDetailsEvent(Mail mailToShow){
		this.mail = mailToShow;
	}
	
	public Mail getMail(){
		return mail;
	}


}
