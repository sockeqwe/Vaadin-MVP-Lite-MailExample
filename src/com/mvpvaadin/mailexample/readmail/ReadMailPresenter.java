package com.mvpvaadin.mailexample.readmail;

import java.io.Serializable;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;
import com.mvpvaadin.presenter.Presenter;

public class ReadMailPresenter extends Presenter<ReadMailView> 
								implements Serializable {

	private static final long serialVersionUID = -3405453759181143858L;
	
	private MailService service;
	private User user;
	
	public ReadMailPresenter(ReadMailView view, EventBus eventBus, User user, MailService service) {
		super(view, eventBus);
		this.service = service;
		this.user = user;
	}
	
	
	public void setMail(Mail mail){
		getView().setMail(mail);
	}

	
	public void markMailAsRead(Mail mail){
		if (!mail.isRead())
		{
			mail.setRead(true);
			int unreadCount = service.getUnreadInboxCountOf(user);
			
			getEventBus().fireEvent(new UnreadCountChangedEvent(unreadCount));
		}
			
	}
	
	
	public void markMailAsUnread(Mail mail){
		if (mail.isRead())
		{
			mail.setRead(false);
			int unreadCount = service.getUnreadInboxCountOf(user);
			getEventBus().fireEvent(new UnreadCountChangedEvent(unreadCount));
		}
	}

	
}
