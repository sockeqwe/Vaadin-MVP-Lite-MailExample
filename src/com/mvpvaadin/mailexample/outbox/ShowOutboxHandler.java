package com.mvpvaadin.mailexample.outbox;

import com.mvplite.event.ShowViewEventHandler;
import com.mvpvaadin.mailexample.data.Mail;

public interface ShowOutboxHandler extends ShowViewEventHandler{

	public void onShowOutboxRequired(Mail preselectedMail);
}
