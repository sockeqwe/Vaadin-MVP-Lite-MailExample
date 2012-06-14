package com.mvpvaadin.mailexample.outbox;

import java.util.Date;
import java.util.List;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.readmail.ShowReadMailEvent;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.view.NavigateableView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class OutboxViewImpl extends Table implements OutboxView{
	
	private static final long serialVersionUID = 13527913492359998L;

	private EventBus eventBus;
	private User user;
	
	private OutboxPresenter presenter;
	
	public OutboxViewImpl(EventBus eventBus, User user){
		this.eventBus = eventBus;
		this.user = user;
		
		generateUI();
		
		presenter = new OutboxPresenter(this, eventBus, MailService.getInstance(), user);
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
		if (!m.isRead())
			receiver = "<b>"+m.getReceiver()+"</b>";
		
		ret[0]= new Label(receiver, Label.CONTENT_XHTML);
		
		String subject= m.getSubject();
		if (!m.isRead())
			subject = "<b>"+m.getSubject()+"</b>";
		ret[1]=new Label(subject, Label.CONTENT_XHTML);
		
		ret[2]=m.getDate();
		
		return ret;
	}

	public void setMails(List<Mail> mails) {
		
		this.removeAllItems();
		
		for (Mail m : mails)
			addItem(generateObjectArrayFromMail(m), m);
		
	}
	
	
	private void generateUI(){
		// Define the names and data types of columns.
		this.addContainerProperty("To", Label.class, null);
		this.addContainerProperty("Subject", Label.class, null);
		this.addContainerProperty("Date", Date.class, null);
		this.setImmediate(true);
		this.setSizeFull();
		this.setColumnReorderingAllowed(true);
		this.setColumnCollapsingAllowed(true);
		this.addListener(new ItemClickListener() {
			
			private static final long serialVersionUID = 6532912566842143136L;

			public void itemClick(ItemClickEvent event) {
			/*	
				Mail mail = (Mail) event.getItemId();
			*/
			}
		});

}

	public com.mvpvaadin.event.Event<? extends EventHandler> getEventToShowThisView() {
		return new ShowOutboxEvent();
	}

	
	public OutboxPresenter getPresenter(){
		return presenter;
	}
	
}
