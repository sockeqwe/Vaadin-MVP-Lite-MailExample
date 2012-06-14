package com.mvpvaadin.mailexample.writemail;

import java.util.Date;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.service.event.NewMailInOutboxEvent;
import com.mvpvaadin.presenter.Presenter;

public class WriteMailPresenter extends Presenter<WriteMailView>{

	private static final long serialVersionUID = -254411078474503751L;
	private User user;
	private MailService mailService;
	
	 private static final String EMAIL_REGEX = 
             "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public WriteMailPresenter(WriteMailView view, EventBus eventBus, User user, MailService mailService) {
		super(view, eventBus);
		this.user = user;
		this.mailService = mailService;
		
	}
	
	private boolean isValidEMail(String mail){
		if (mail.matches(EMAIL_REGEX))
			return true;
		else
			return false;
	}
	
	public void attemptWriteMail(String receiver, String subject, String text){
		
		if (receiver==null || receiver.isEmpty() || !isValidEMail(receiver)){
			getView().showErrorMessage("Receivers email address is not valid");
			return;
		}
		
		if (subject==null || subject.isEmpty() ){
			getView().showErrorMessage("Subject is empty");
			return;
		}
		
		
		if (text==null || text.isEmpty() ){
			getView().showErrorMessage("Message body text is empty");
			return;
		}
		
		Mail mail = new Mail(user.getEmailAddress(), receiver, subject, text, new Date());
		mailService.sendMail(user, mail);
		
		getView().showSuccessfulMessage();
		getView().clearForm();
		
		getEventBus().fireEvent(new NewMailInOutboxEvent(mail));
		
	}

}
