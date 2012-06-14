package com.mvpvaadin.mailexample.inbox;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedHandler;
import com.mvpvaadin.presenter.Presenter;

public class InboxPresenter extends Presenter<InboxView> implements UnreadCountChangedHandler{

	private static final long serialVersionUID = -4182522683123537034L;
	
	private MailService mailService;
	private User user;
	
	public InboxPresenter(InboxView view, EventBus eventBus, User user, MailService mailService) {
		super(view, eventBus);
		this.mailService = mailService;
		this.user = user;
		bind();
		refreshMails();
	}
	
	private void bind(){
		getEventBus().addHandler(UnreadCountChangedEvent.TYPE, this);
	}
	
	public void refreshMails(){
		getView().setMails(mailService.getInboxMailsOf(user));
	}

	public void onUnreadCountChanged(int newUnreadValue) {
		refreshMails();
	}

}
