package com.mvpvaadin.mailexample.statistics;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxEvent;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxHandler;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedHandler;
import com.mvpvaadin.presenter.Presenter;

public class StatisticsPresenter extends Presenter<StatisticsView> implements UnreadCountChangedHandler, NewMailInOutboxHandler{

	private static final long serialVersionUID = -7907450349081515874L;
	
	private User user;
	private MailService service;
	
	public StatisticsPresenter(StatisticsView view, EventBus eventBus, 
			User user, MailService mailService) {
		super(view, eventBus);
		this.user = user;
		this.service = mailService;
		
		bind();
		
	}
	
	
	private void bind(){
		getEventBus().addHandler(UnreadCountChangedEvent.TYPE, this);
		getEventBus().addHandler(NewMailInOutboxEvent.TYPE, this);
	}
	
	
	public void refreshStatistics(){
		getView().setUnreadMailsCount(service.getUnreadInboxCountOf(user));
		getView().setUsername(user.getUsername());
		getView().setEmailAddress(user.getEmailAddress());
		getView().setOutboxMailCount(service.getOutboxCountOf(user));
	}
	

	public void onUnreadCountChanged(int newUnreadValue) {
		getView().setUnreadMailsCount(newUnreadValue);
	}



	public void onNewMailInOutbox(Mail mail) {
		getView().setOutboxMailCount(service.getOutboxCountOf(user));
	}
	
	
	

	
	
}
