package com.mvpvaadin.mailexample.statistics;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxEvent;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;

public class StatisticsPresenter extends Presenter<StatisticsView> {

	private static final long serialVersionUID = -7907450349081515874L;
	
	private final User user;
	private final MailService service;
	
	public StatisticsPresenter(StatisticsView view, EventBus eventBus, 
			User user, MailService mailService) {
		super(view, eventBus);
		this.user = user;
		this.service = mailService;
		
		bind();
		
	}
	
	
	private void bind(){
		getEventBus().addHandler(this);
	}
	
	
	public void refreshStatistics(){
		getView().setUnreadMailsCount(service.getUnreadInboxCountOf(user));
		getView().setUsername(user.getUsername());
		getView().setEmailAddress(user.getEmailAddress());
		getView().setOutboxMailCount(service.getOutboxCountOf(user));
	}
	

	
	@EventHandler
	public void onUnreadCountChanged(UnreadCountChangedEvent e) {
		getView().setUnreadMailsCount(e.getUnreadCount());
	}



	@EventHandler
	public void onNewMailInOutbox(NewMailInOutboxEvent e) {
		getView().setOutboxMailCount(service.getOutboxCountOf(user));
	}
	
	
	

	
	
}
