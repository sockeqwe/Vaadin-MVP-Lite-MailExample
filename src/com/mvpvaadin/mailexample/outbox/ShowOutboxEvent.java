package com.mvpvaadin.mailexample.outbox;

import com.mvplite.event.ShowViewEvent;
import com.mvpvaadin.mailexample.data.Mail;

/**
 * This event is fired to bring the {@link OutboxView} on screen
 * with the {@link #getPreselectedMail()} Mail as preselected.
 * @author Hannes Dorfmann
 *
 */
public class ShowOutboxEvent extends ShowViewEvent{

	private static final long serialVersionUID = 2291299420081670227L;

	private final Mail preselectedMail;
	
	public ShowOutboxEvent(Mail preselectedMail){
		this.preselectedMail = preselectedMail;
	}
	
	
	public Mail getPreselectedMail(){
		return preselectedMail;
	}
	
}
