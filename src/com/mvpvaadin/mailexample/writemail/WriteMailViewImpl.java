package com.mvpvaadin.mailexample.writemail;

import com.mvplite.event.EventBus;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class WriteMailViewImpl extends Window implements WriteMailView{
	
	private static final long serialVersionUID = -5065377876430244428L;

	private WriteMailPresenter presenter;
	
	private TextField addressField;
	private TextField subjectField;
	private TextArea messageField;
	
	
	public WriteMailViewImpl(EventBus eventBus, User user, MailService mailService){
		super("Write mail");
		generateUI();
		presenter = new WriteMailPresenter(this, eventBus, user, mailService);
	}
	
	
	private void generateUI(){
		this.setWidth("40%");
		this.setHeight("50%");
		
		VerticalLayout layout = new VerticalLayout();
		
		addressField = new TextField("Receiver");
		subjectField = new TextField("Subject");
		messageField = new TextArea("Message");
		
		Button sendButton = new Button("Send");
		sendButton.setClickShortcut(KeyCode.ENTER);
		sendButton.addListener(new ClickListener() {
			
			private static final long serialVersionUID = -8748444774015034730L;

			public void buttonClick(ClickEvent event) {
				presenter.attemptWriteMail((String)addressField.getValue(), 
						(String) subjectField.getValue(), (String) messageField.getValue());
			}
		});
		
		addressField.setWidth("100%");
		subjectField.setWidth("100%");
		messageField.setWidth("100%");
		
		layout.addComponent(addressField);
		layout.addComponent(subjectField);
		layout.addComponent(messageField);
		layout.addComponent(sendButton);
		layout.setSizeFull();
		layout.setExpandRatio(messageField, 1);
		messageField.setSizeFull();
		layout.setComponentAlignment(sendButton, Alignment.MIDDLE_CENTER);
		
		this.setContent(layout);
		this.setModal(true);
		this.center();
	}

	public void showSuccessfulMessage() {
		showNotification("Successful", Notification.TYPE_HUMANIZED_MESSAGE);
		this.close();
	}

	public void showErrorMessage(String reason) {
		showNotification(reason, Notification.TYPE_WARNING_MESSAGE);
	}


	public void clearForm() {
		addressField.setValue("");
		subjectField.setValue("");
		messageField.setValue("");
	}


	public void setReceiverMailAddress(String mailAddress) {
		addressField.setValue(mailAddress);
	}


	public void setSubject(String subject) {
		subjectField.setValue(subject);
	}

}
