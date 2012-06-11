package com.mvpvaadin.mailexample.inbox;

import java.util.List;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.view.NavigateableView;
import com.vaadin.ui.Table;

public class InboxViewImpl extends Table implements InboxView{

	private static final long serialVersionUID = 2375843281754049077L;
	
	private NavigateableView parent;
	private EventBus eventBus;
	
	private InboxPresenter presenter;
	
	public InboxViewImpl(NavigateableView parent, EventBus eventBus, User user){
	
		this.parent = parent;
		this.eventBus = eventBus;
		generateUI();
		
		presenter = new InboxPresenter(this, eventBus, user, MailService.getInstance());
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
		eventBus.fireEvent(new ShowInboxViewRequiredEvent());
	}

	public void setMails(List<Mail> mails) {
		
//		setContainerDataSource(newDataSource);
	}
	
	
	private void generateUI(){
		
	}


	public com.mvpvaadin.event.Event<? extends EventHandler> getEventToShowThisView() {
		return new ShowInboxViewRequiredEvent();
	}


	public InboxPresenter getPresenter() {
		return presenter;
	}
	
	

}
