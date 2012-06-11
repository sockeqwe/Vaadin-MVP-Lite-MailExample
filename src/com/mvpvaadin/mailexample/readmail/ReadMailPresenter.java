package com.mvpvaadin.mailexample.readmail;

import java.io.Serializable;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;
import com.mvpvaadin.presenter.Presenter;

public class ReadMailPresenter extends Presenter<ReadMailView> implements Serializable{

	private static final long serialVersionUID = -3405453759181143858L;
	
	private Mail mail;
	private MailService service;
	private User user;
	
	public ReadMailPresenter(ReadMailView view, EventBus eventBus, User user, MailService service) {
		super(view, eventBus);
		this.service = service;
	}
	
	
	public void setMail(Mail mail){
		this.mail = mail;
		getView().setMail(mail);
	}

	
	public void markMailAsRead(){
		if (!mail.isRead())
		{
			mail.setRead(true);
			int unreadCount = service.getUnreadInboxCountOf(user);
			
			getEventBus().fireEvent(new UnreadCountChangedEvent(unreadCount));
		}
			
			
	}
	
	
}
