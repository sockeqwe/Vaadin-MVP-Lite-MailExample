package com.mvpvaadin.mailexample.readmail;

import com.mvplite.event.ShowViewEventHandler;
import com.mvpvaadin.mailexample.data.Mail;

public interface ShowReadMailRequiredHandler extends ShowViewEventHandler{
	
	public void onReadMailRequired(Mail mail);

}
