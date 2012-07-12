package com.mvpvaadin.mailexample.inbox;

import java.util.Date;
import java.util.List;

import com.mvplite.event.EventBus;
import com.mvplite.event.ShowViewEvent;
import com.mvplite.event.ShowViewEventHandler;
import com.mvplite.view.NavigateableView;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.readmail.ShowReadMailEvent;
import com.mvpvaadin.mailexample.service.MailService;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class InboxViewImpl extends Table implements InboxView{

	private static final long serialVersionUID = 2375843281754049077L;
	
	private NavigateableView parent;
	private EventBus eventBus;
	
	
	private InboxPresenter presenter;
	
	public InboxViewImpl(NavigateableView parent, EventBus eventBus, 
			User user, MailService service){
	
		this.parent = parent;
		this.eventBus = eventBus;
		generateUI();
		
		presenter = new InboxPresenter(this, eventBus, user, service);
	}
	
	
	public NavigateableView getParentView() {
		return parent;
	}

	public String getUriFragment() {
		return "inbox";
	}

	public String getBreadcrumbTitle() {
		return "Inbox";
	}

	public void fireEventToShowThisView() {
		eventBus.fireEvent(new ShowInboxViewEvent());
	}
	
	
	private Object[] generateObjectArrayFromMail(Mail m){
		Object[] ret = new Object[3];
		
		String receiver = m.getSender();
		if (!m.isRead())
			receiver = "<b>"+m.getSender()+"</b>";
		
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
		this.addContainerProperty("From", Label.class, null);
		this.addContainerProperty("Subject", Label.class, null);
		this.addContainerProperty("Date", Date.class, null);
		this.setImmediate(true);
		this.setSizeFull();
		this.setColumnReorderingAllowed(true);
		this.setColumnCollapsingAllowed(true);
		this.addListener(new ItemClickListener() {
			
			private static final long serialVersionUID = 6532912566842143136L;

			public void itemClick(ItemClickEvent event) {
				
				Mail mail = (Mail) event.getItemId();
				eventBus.fireEvent(new ShowReadMailEvent(mail));
			}
		});
		
	}


	public ShowViewEvent <? extends ShowViewEventHandler> getEventToShowThisView() {
		return new ShowInboxViewEvent();
	}


	public InboxPresenter getPresenter() {
		return presenter;
	}
	
	

}
