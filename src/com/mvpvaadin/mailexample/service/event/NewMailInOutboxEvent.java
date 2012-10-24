package com.mvpvaadin.mailexample.service.event;

import com.mvplite.event.Event;
import com.mvpvaadin.mailexample.data.Mail;

/**
 * This event is fired to notify, that a new {@link Mail} is stored in the outbox
 * @author Hannes Dorfmann
 *
 */
public class NewMailInOutboxEvent extends Event{
	
	private static final long serialVersionUID = -8471872653379633445L;

	
	
	private final Mail mail;
	
	public NewMailInOutboxEvent(Mail newMail){
		this.mail = newMail;
	}
	
	
	public Mail getMail(){
		return mail;
	}

}
