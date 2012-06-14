package com.mvpvaadin.mailexample.service.event;

import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.Mail;

public interface NewMailInOutboxHandler extends EventHandler{
	
	public void onNewMailInOutbox(Mail mail);

}
