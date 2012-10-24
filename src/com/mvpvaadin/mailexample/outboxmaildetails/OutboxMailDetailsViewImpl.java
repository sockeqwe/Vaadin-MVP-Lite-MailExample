package com.mvpvaadin.mailexample.outboxmaildetails;

import com.mvplite.event.EventBus;
import com.mvplite.event.ShowViewEvent;
import com.mvplite.view.NavigateableSubView;
import com.mvplite.view.NavigateableView;
import com.mvpvaadin.mailexample.data.Mail;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class OutboxMailDetailsViewImpl extends VerticalLayout implements OutboxMailDetailsView,  NavigateableSubView{

	private static final long serialVersionUID = 6755094650406479739L;

	private final OutboxMailDetailsPresenter presenter;
	private final NavigateableView parent;
	
	private Label fromLabel;
	private Label toLabel;
	private Label subjectLabel;
	private Label dateLabel;
	private Label messageBodyLabel;
	
	
	public OutboxMailDetailsViewImpl(EventBus eventBus, NavigateableView parent){
		this.parent = parent;
		generateUI();
		presenter = new OutboxMailDetailsPresenter(this, eventBus);
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
		
		this.setSizeFull();
		
		this.addComponent(fromLabel);
		this.addComponent(toLabel);
		this.addComponent(dateLabel);
		this.addComponent(subjectLabel);
		this.addComponent(messageBodyLabel);
		this.setExpandRatio(messageBodyLabel, 1);
		
		this.setStyleName("outboxDetailView");
	}

	public OutboxMailDetailsPresenter getPresenter(){
		return presenter;
	}

	public void setMailToPreview(Mail mail) {
		fromLabel.setValue("<b>From:</b> "+mail.getSender());
		toLabel.setValue("<b>To:</b> "+mail.getReceiver());
		dateLabel.setValue("<b>Date:</b> "+mail.getDate());
		subjectLabel.setValue("<b>Subject:</b> "+mail.getSubject());
		messageBodyLabel.setValue(mail.getMessage());
	}


	public String getUriFragment() {
		return ""+presenter.getMail().getId();
	}


	public String getBreadcrumbTitle() {
		return presenter.getMail().getSubject();
	}


	public ShowViewEvent getEventToShowThisView() {
		return new ShowOutboxMailDetailsEvent(presenter.getMail());
	}


	public NavigateableView getParentView() {
		return parent;
	}
	
}
