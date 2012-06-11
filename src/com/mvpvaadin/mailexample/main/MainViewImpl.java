package com.mvpvaadin.mailexample.main;

import java.io.Serializable;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.event.EventHandler;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.login.LogoutEvent;
import com.mvpvaadin.mailexample.service.MailService;
import com.mvpvaadin.mailexample.statistics.ShowStatisticsViewEvent;
import com.mvpvaadin.mailexample.statistics.ShowStatisticsViewHandler;
import com.mvpvaadin.mailexample.statistics.StatisticsViewImpl;
import com.mvpvaadin.view.NavigateableView;
import com.mvpvaadin.view.NavigationController;
import com.mvpvaadin.view.ui.Breadcrumbs;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainViewImpl extends VerticalLayout implements MainView, Serializable, NavigateableView,
															ShowStatisticsViewHandler{

	private static final long serialVersionUID = -8755323074558632618L;
	
	private NavigationController navigationController;
	private EventBus eventBus ;
	private User user;
	private MainPresenter mainPresenter;
	private Button inboxButton;
	
	private VerticalLayout subViewContainer;
	
	private VerticalLayout startView;
	
	// Subviews
	private StatisticsViewImpl welcomeView;
	
	
	
	public MainViewImpl(User user, EventBus eventBus, NavigationController navigationController){
		this.eventBus = eventBus;
		this.navigationController = navigationController;
		this.user = user;

		generateUI();
		
		this.mainPresenter = new MainPresenter(this, eventBus, user, MailService.getInstance());
		bind();
		this.setSizeFull();
		
	}
	
	
	private void bind(){
		eventBus.addHandler(ShowStatisticsViewEvent.TYPE, this);
	}
	
	
	public String getUriFragment() {
		return "home";
	}

	public String getBreadcrumbTitle() {
		return "Home";
	}

	

	
	private void generateUI(){
		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.addStyleName("header");
		
		Label logoLabel = new Label("&nbsp;&nbsp; Mail MVP Example",Label.CONTENT_XHTML);
		logoLabel.setStyleName("h1");
		
		
		header.addComponent(logoLabel);
		header.setComponentAlignment(logoLabel, Alignment.MIDDLE_LEFT);
		
		Label usernameLabel = new Label("Hi "+user.getEmailAddress());
		
		
		Button logoutButton = new Button("logout");
		logoutButton.setStyleName("default");
		logoutButton.addListener(new ClickListener() {
			
			private static final long serialVersionUID = 5567732768481757445L;

			public void buttonClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent(user));
			}
		});
		
		inboxButton = new Button("inbox");
		inboxButton.setStyleName("default");
		
		Button outboxButton = new Button("outbox");
		outboxButton.setStyleName("default");
		
		Button writeMail = new Button("new mail");
		writeMail.setStyleName("default");
		
		Button statisticsButton = new Button("statistics");
		statisticsButton.setStyleName("default");
		statisticsButton.addListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				eventBus.fireEvent(new ShowStatisticsViewEvent());
			}
		});
		
		HorizontalLayout headerBar = new HorizontalLayout();
		headerBar.setStyleName("headerBar");
		
		headerBar.addComponent(usernameLabel);
		headerBar.addComponent(inboxButton);
		headerBar.addComponent(writeMail);
		headerBar.addComponent(statisticsButton);
		headerBar.addComponent(outboxButton);
		headerBar.addComponent(logoutButton);
		
		headerBar.setComponentAlignment(usernameLabel, Alignment.MIDDLE_CENTER);
		
		
		headerBar.setSizeUndefined();
		headerBar.setSpacing(true);
		
		header.setSpacing(true);
		header.addComponent(headerBar);
		header.setComponentAlignment(headerBar, Alignment.MIDDLE_RIGHT);
		
		
		// add the Breadcrumb
		Breadcrumbs breadcrumbs = new Breadcrumbs(navigationController);
		breadcrumbs.setStyleName("breadcrumbs");
		breadcrumbs.setWidth("100%");
		
		
		// subViewContainer is the container where each subview is placed in 
		subViewContainer = new VerticalLayout();
		subViewContainer.setSizeFull();
		subViewContainer.setStyleName("subViewContainer");
		
		this.addComponent(header);
		this.addComponent(breadcrumbs);
		this.addComponent(subViewContainer);
		
		this.setComponentAlignment(header, Alignment.TOP_LEFT);
		this.setComponentAlignment(breadcrumbs, Alignment.TOP_LEFT);
		this.setComponentAlignment(subViewContainer, Alignment.TOP_LEFT);
		
		setExpandRatio(subViewContainer, 1);
		this.setSizeFull();
	}
	
	
	
	
	public void setSubview(Component subview){
		
		subViewContainer.removeAllComponents();
		subViewContainer.addComponent(subview);
		
	}
	
	
	


	public void setInboxUnreadCount(int unread) {
		String caption = "inbox";
		if (unread>0)
			caption = "inbox ("+unread+")";
		inboxButton.setCaption(caption);
	}

	
	public void showStartSubView(){
		if (startView == null){
			startView = new VerticalLayout();
			
			Label title = new Label("Welcome "+user.getUsername());
			title.setStyleName("h2");
			startView.addComponent(title);
			startView.addComponent(new Label("Use the main menu"));
		}
		
		setSubview(startView);
		navigationController.setCurrentView(this);
		
	}
	
	public void onShowWelcomeViewRequired() {
		if (welcomeView == null)
			welcomeView = new StatisticsViewImpl(this, eventBus, user);
		
		setSubview(welcomeView);
		navigationController.setCurrentView(welcomeView);
	}


	public com.mvpvaadin.event.Event<? extends EventHandler> getEventToShowThisView() {
		return new ShowMainViewEvent();
	}
	
		
	
}
