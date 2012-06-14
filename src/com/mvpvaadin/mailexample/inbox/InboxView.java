package com.mvpvaadin.mailexample.inbox;

import java.util.List;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.view.NavigateableSubView;


public interface InboxView extends NavigateableSubView{

	public void setMails(List<Mail> mails);
	
}
