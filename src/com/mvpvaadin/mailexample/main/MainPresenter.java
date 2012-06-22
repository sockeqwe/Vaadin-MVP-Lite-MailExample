package com.mvpvaadin.mailexample.main;

import java.io.Serializable;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedHandler;
import com.mvpvaadin.presenter.Presenter;

public class MainPresenter extends Presenter<MainView> implements UnreadCountChangedHandler, Serializable{
	
	private static final long serialVersionUID = -9824276818586872L;
	
	private User user;
	private MailService mailService;

	public MainPresenter(MainView view, EventBus eventBus, User user, MailService mailService) {
		super(view, eventBus);
		this.user = user;
		this.mailService = mailService;
		bind();
		
		// Set the initial unreadcount
		getView().setInboxUnreadCount(mailService.getUnreadInboxCountOf(user));
	}
	
	private void bind(){
		getEventBus().addHandler(UnreadCountChangedEvent.TYPE, this);
	}

	public void onUnreadCountChanged(int newUnreadValue) {
		getView().setInboxUnreadCount(newUnreadValue);
	}

	public MailService getMailService() {
		return mailService;
	}
	
	
	
	
	

}
