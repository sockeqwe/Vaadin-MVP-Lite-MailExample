package com.mvpvaadin.mailexample.outboxmaildetails;

import com.mvplite.event.EventBus;
import com.mvplite.presenter.Presenter;
import com.mvpvaadin.mailexample.data.Mail;

public class OutboxMailDetailsPresenter extends Presenter<OutboxMailDetailsView>{

	private static final long serialVersionUID = 8169514748033467836L;

	private Mail mail;
	
	public OutboxMailDetailsPresenter(OutboxMailDetailsView view, EventBus eventBus) {
		super(view, eventBus);
		
	}
	
	
	public void setMail(Mail mail){
		this.mail = mail;
		getView().setMailToPreview(mail);
	}

	public Mail getMail(){
		return mail;
	}
}
