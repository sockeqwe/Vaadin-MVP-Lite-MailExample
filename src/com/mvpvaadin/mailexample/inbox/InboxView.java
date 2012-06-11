package com.mvpvaadin.mailexample.inbox;

import java.util.List;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.view.SubView;

public interface InboxView extends SubView{

	public void setMails(List<Mail> mails);
	
}
