package com.mvpvaadin.mailexample.outbox;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxEvent;

public class OutboxPresenter extends Presenter<OutboxView> {
	
	private static final long serialVersionUID = 3224474820958279963L;

	private final MailService mailService;
	private final User user;
	

	public OutboxPresenter(OutboxView view, EventBus eventBus, MailService mailService, User user) {
		super(view, eventBus);
		
		this.user = user;
		this.mailService = mailService;
		bind();
	}
	
	private void bind(){
		getEventBus().addHandler(this);
	}
	
	public void refreshList(){
		getView().setMails(mailService.getOutboxMailsOf(user));
	}

	@EventHandler
	public void onNewMailInOutbox(NewMailInOutboxEvent e) {
		// We are lazy, so refresh the whole list
		refreshList();
	}
	
	
	public void setPreselectedMail(Mail mail){
		getView().preselectMail(mail);
	}

}
