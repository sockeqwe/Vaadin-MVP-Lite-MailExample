package com.mvpvaadin.mailexample.outbox;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxEvent;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxHandler;
import com.mvpvaadin.presenter.Presenter;

public class OutboxPresenter extends Presenter<OutboxView> implements NewMailInOutboxHandler{
	
	private static final long serialVersionUID = 3224474820958279963L;

	private MailService mailService;
	private User user;

	public OutboxPresenter(OutboxView view, EventBus eventBus, MailService mailService, User user) {
		super(view, eventBus);
		
		this.user = user;
		this.mailService = mailService;
		bind();
	}
	
	private void bind(){
		getEventBus().addHandler(NewMailInOutboxEvent.TYPE, this);
	}
	
	public void refreshList(){
		getView().setMails(mailService.getOutboxMailsOf(user));
	}

	public void onNewMailInOutbox(Mail mail) {
		// We are lazy, so refresh the whole list
		refreshList();
	}

}
