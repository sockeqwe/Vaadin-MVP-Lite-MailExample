package com.mvpvaadin.mailexample.outboxmaildetails;

import com.mvplite.event.ShowViewEventHandler;
import com.mvpvaadin.mailexample.data.Mail;

public interface ShowOutboxMailDetailsHandler extends ShowViewEventHandler {

	public void onShowOutboxMailDetailsRequired(Mail mail);
}
