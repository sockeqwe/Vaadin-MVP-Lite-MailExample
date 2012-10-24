package com.mvpvaadin.mailexample.readmail;

import com.mvplite.event.ShowViewEvent;
import com.mvpvaadin.mailexample.data.Mail;

/**
 * This event is fired to bring the "read mail" view on screen
 * with the given mail ({@link #getMail()})
 * @author Hannes Dorfmann
 *
 */
public class ShowReadMailEvent extends ShowViewEvent{

	private static final long serialVersionUID = -935406143959337075L;
	private final Mail mail;
	
	public ShowReadMailEvent(Mail mail){
		this.mail = mail;
	}
	
	
	public Mail getMail(){
		return mail;
	}
	
	

}
