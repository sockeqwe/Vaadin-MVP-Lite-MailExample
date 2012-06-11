package com.mvpvaadin.mailexample.readmail;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.view.NavigateableView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ReadMailViewImpl extends VerticalLayout implements ReadMailView{

	private static final long serialVersionUID = -582201495923291510L;

	private NavigateableView parent;
	private Mail mail;
	
	private Label fromLabel;
	private Label toLabel;
	private Label subjectLabel;
	private Label dateLabel;
	private Label messageBodyLabel;
	
	public ReadMailViewImpl(NavigateableView parent, EventBus eventBus){
		this.parent = parent;
		
		generateUI();
	}
	
	private void generateUI(){
		fromLabel = new Label();
		fromLabel.setContentMode(Label.CONTENT_XHTML);
		toLabel = new Label();
		toLabel.setContentMode(Label.CONTENT_XHTML);
		subjectLabel = new Label();
		subjectLabel.setContentMode(Label.CONTENT_XHTML);
		dateLabel = new Label();
		dateLabel.setContentMode(Label.CONTENT_XHTML);
		messageBodyLabel = new Label();
		messageBodyLabel.setContentMode(Label.CONTENT_XHTML);
		
		
		this.addComponent(fromLabel);
		this.addComponent(toLabel);
		this.addComponent(dateLabel);
		this.addComponent(subjectLabel);
		this.addComponent(messageBodyLabel);
		
		
		this.setSizeFull();
		this.setComponentAlignment(fromLabel, Alignment.MIDDLE_LEFT);
		this.setComponentAlignment(toLabel, Alignment.MIDDLE_LEFT);
		this.setComponentAlignment(dateLabel, Alignment.MIDDLE_LEFT);
		this.setComponentAlignment(subjectLabel, Alignment.MIDDLE_LEFT);
		this.setComponentAlignment(messageBodyLabel, Alignment.MIDDLE_LEFT);
		
		this.setExpandRatio(messageBodyLabel, 1);
	}
	
	public NavigateableView getParentView() {
		return parent;
	}

	public String getUriFragment() {
		return "mail";
	}

	public String getBreadcrumbTitle() {
		return mail.getSubject();
	}

	public void setMail(Mail mail) {
		this.mail = mail;
		fromLabel.setCaption("<b>From:</b> "+mail.getSender());
		toLabel.setCaption("<b>To:</b> "+mail.getReceiver());
		dateLabel.setCaption("<b>Date:</b> "+mail.getDate());
		subjectLabel.setCaption("<b>Subject:</b> "+mail.getSubject());
		messageBodyLabel.setCaption(mail.getMessage());
		
	}

	public com.mvpvaadin.event.Event<? extends EventHandler> getEventToShowThisView() {
		return new ShowReadMailEvent(mail);
	}

}
