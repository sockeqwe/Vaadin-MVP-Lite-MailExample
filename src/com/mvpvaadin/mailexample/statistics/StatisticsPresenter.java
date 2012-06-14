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
	
	public StatisticsPresenter(StatisticsView view, EventBus eventBus, User user) {
		super(view, eventBus);
		this.user = user;
		
		getEventBus().addHandler(UnreadCountChangedEvent.TYPE, this);
		getEventBus().addHandler(NewMailInOutboxEvent.TYPE, this);
		
		// set the initial displaying data
		getView().setUnreadMailsCount(MailService.getInstance().getUnreadInboxCountOf(user));
		getView().setUsername(user.getUsername());
		getView().setEmailAddress(user.getEmailAddress());
		getView().setOutboxMailCount(MailService.getInstance().getOutboxCountOf(user));
		
	}
	
	

	public void onUnreadCountChanged(int newUnreadValue) {
		getView().setUnreadMailsCount(newUnreadValue);
	}



	public void onNewMailInOutbox(Mail mail) {
		getView().setOutboxMailCount(MailService.getInstance().getOutboxCountOf(user));
	}
	
	
	

	
	
}
