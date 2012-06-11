package com.mvpvaadin.mailexample.readmail;

import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.Mail;

public interface ShowReadMailRequiredHandler extends EventHandler{
	
	public void onMailReviewRequired(Mail mail);

}
