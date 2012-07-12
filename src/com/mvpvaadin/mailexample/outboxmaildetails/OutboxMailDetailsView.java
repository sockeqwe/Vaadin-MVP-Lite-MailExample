package com.mvpvaadin.mailexample.outboxmaildetails;

import com.mvplite.view.View;
import com.mvpvaadin.mailexample.data.Mail;

public interface OutboxMailDetailsView extends View {

	public void setMailToPreview(Mail mail);
	
}
