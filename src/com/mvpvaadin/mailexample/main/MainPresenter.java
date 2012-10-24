package com.mvpvaadin.mailexample.main;

import java.io.Serializable;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;

public class MainPresenter extends Presenter<MainView> implements  Serializable{
	
	private static final long serialVersionUID = -9824276818586872L;
	
	private final User user;
	private final MailService mailService;

	public MainPresenter(MainView view, EventBus eventBus, User user, MailService mailService) {
		super(view, eventBus);
		this.user = user;
		this.mailService = mailService;
		bind();
		
		// Set the initial unreadcount
		getView().setInboxUnreadCount(mailService.getUnreadInboxCountOf(user));
	}
	
	private void bind(){
		getEventBus().addHandler(this);
	}

	@EventHandler
	public void onUnreadCountChanged(UnreadCountChangedEvent e) {
		getView().setInboxUnreadCount(e.getUnreadCount());
	}

	public MailService getMailService() {
		return mailService;
	}
	
	
	
	
	

}
