package com.mvpvaadin.mailexample.inbox;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.MailReceivedEvent;
import com.mvpvaadin.mailexample.service.event.UnreadCountChangedEvent;

public class InboxPresenter extends Presenter<InboxView>{

	private static final long serialVersionUID = -4182522683123537034L;
	
	private final MailService mailService;
	private final User user;
	
	public InboxPresenter(InboxView view, EventBus eventBus, User user, MailService mailService) {
		super(view, eventBus);
		this.mailService = mailService;
		this.user = user;
		bind();
		refreshMails();
	}
	
	private void bind(){
		getEventBus().addHandler(this);
	}
	
	public void refreshMails(){
		getView().setMails(mailService.getInboxMailsOf(user));
	}

	@EventHandler
	public void onUnreadCountChanged(UnreadCountChangedEvent e) {
		refreshMails();
	}

	@EventHandler
	public void onNewMailReceived(MailReceivedEvent e){
		refreshMails();
	}
}
