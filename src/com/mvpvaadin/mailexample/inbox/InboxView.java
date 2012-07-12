package com.mvpvaadin.mailexample.inbox;

import java.util.List;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvplite.view.NavigateableSubView;


public interface InboxView extends NavigateableSubView{

	public void setMails(List<Mail> mails);
	
}
