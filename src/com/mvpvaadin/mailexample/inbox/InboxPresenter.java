package com.mvpvaadin.mailexample.inbox;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.presenter.Presenter;

public class InboxPresenter extends Presenter<InboxView>{

	private MailService mailService;
	private User user;
	
	public InboxPresenter(InboxView view, EventBus eventBus, User user, MailService mailService) {
		super(view, eventBus);
		this.mailService = mailService;
		this.user = user;
		refreshMails();
	}
	
	private void refreshMails(){
		getView().setMails(mailService.getInboxMailsOf(user));
	}

}
