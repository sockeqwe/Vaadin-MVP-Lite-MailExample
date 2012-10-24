package com.mvpvaadin.mailexample.service.event;

import com.mvplite.event.Event;
import com.mvpvaadin.mailexample.data.Mail;

/**
 * This Event is fired to inform, 
 * that the current user has received a Mail (inbox).
 * @author Hannes Dorfmann
 *
 */
public class MailReceivedEvent extends Event{

	private static final long serialVersionUID = 7891012690663165479L;
	
	private final Mail mail;
	
	public MailReceivedEvent(Mail mail){
		this.mail = mail;
	}
	
	public Mail getMail(){
		return mail;
	}
	
}
