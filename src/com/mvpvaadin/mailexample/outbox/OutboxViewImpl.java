package com.mvpvaadin.mailexample.outbox;

import java.util.Date;
import java.util.List;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.event.ShowViewEvent;
import com.mvplite.view.NavigationController;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.outbox.ui.SelectHintView;
import com.mvpvaadin.mailexample.outboxmaildetails.OutboxMailDetailsViewImpl;
import com.mvpvaadin.mailexample.outboxmaildetails.ShowOutboxMailDetailsEvent;
import com.mvpvaadin.mailexample.service.MailService;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

public class OutboxViewImpl extends VerticalSplitPanel implements OutboxView{
	
	private static final long serialVersionUID = 13527913492359998L;

	private final EventBus eventBus;
	private final NavigationController navigationController;
	private final OutboxPresenter presenter;
	
	private Table mailTable;
	private SelectHintView selectHintView;
	private Mail currentSelectedMail;
	
	// SubViews
	private OutboxMailDetailsViewImpl mailDetailsView;
	
	public OutboxViewImpl(EventBus eventBus, User user, MailService mailService, NavigationController controller){
		this.eventBus = eventBus;
		this.navigationController = controller;
		generateUI();
		bind();
		
		presenter = new OutboxPresenter(this, eventBus, mailService, user);
	}
	
	private void bind(){
		eventBus.addHandler(this);
	}
	
	public String getUriFragment() {
		return "outbox";
	}

	public String getBreadcrumbTitle() {
		return "Outbox";
	}

	

	private Object[] generateObjectArrayFromMail(Mail m){
		Object[] ret = new Object[3];
		
		String receiver = m.getReceiver();
		
		ret[0]= new Label(receiver, Label.CONTENT_XHTML);
		
		String subject= m.getSubject();
		ret[1]=new Label(subject, Label.CONTENT_XHTML);
		
		ret[2]=m.getDate();
		
		return ret;
	}

	public void setMails(List<Mail> mails) {
		
		mailTable.removeAllItems();
		
		for (Mail m : mails)
			mailTable.addItem(generateObjectArrayFromMail(m), m);
		
	}
	
	
	private void generateUI(){
		
		selectHintView = new SelectHintView("top");
		
		this.setSizeFull();
		mailTable = new Table();
		
		// Define the names and data types of columns.
		mailTable.addContainerProperty("To", Label.class, null);
		mailTable.addContainerProperty("Subject", Label.class, null);
		mailTable.addContainerProperty("Date", Date.class, null);
		mailTable.setImmediate(true);
		mailTable.setSizeFull();
		mailTable.setColumnReorderingAllowed(true);
		mailTable.setColumnCollapsingAllowed(true);
		mailTable.setSelectable(true);
		mailTable.addListener(new Property.ValueChangeListener() {
			
			private static final long serialVersionUID = -2985353369890003897L;

			public void valueChange(ValueChangeEvent event) {
				Mail mail = (Mail) event.getProperty().getValue();
				currentSelectedMail = mail;
				eventBus.fireEvent(new ShowOutboxMailDetailsEvent(mail));
			}
		});

		
		this.setFirstComponent(mailTable);
		this.setSecondComponent(selectHintView);
		this.setSplitPosition(50, Sizeable.UNITS_PERCENTAGE);
		
		
}

	public ShowViewEvent getEventToShowThisView() {
		return new ShowOutboxEvent(currentSelectedMail);
	}

	
	public OutboxPresenter getPresenter(){
		return presenter;
	}


	@EventHandler
	public void onShowOutboxMailDetailsRequired(ShowOutboxMailDetailsEvent e) {
		
		if (e.getMail() == null){
			this.setSecondComponent(selectHintView);
			navigationController.setCurrentView(this);
		}
		else
		{
			if (mailDetailsView == null)
				mailDetailsView = new OutboxMailDetailsViewImpl(eventBus, this);
			
			mailDetailsView.getPresenter().setMail(e.getMail());
			navigationController.setCurrentView(mailDetailsView);
			this.setSecondComponent(mailDetailsView);
		}
		
	}

	public void preselectMail(Mail mail) {
		if (mail == null)
			this.setSecondComponent(selectHintView);
		
		mailTable.setValue(mail);
	}
	
}
