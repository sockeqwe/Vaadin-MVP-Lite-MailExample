package com.mvpvaadin.mailexample.login;

import java.io.Serializable;

import com.mvplite.event.EventBus;
import com.mvpvaadin.mailexample.service.AuthenticationService;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LoginViewImpl extends VerticalLayout implements LoginView, Serializable{

	private static final long serialVersionUID = 7744334012652777834L;
	
	private final LoginPresenter presenter;
	
	private Button loginButton;
	private Button clearButton;
	private TextField usernameField;
	private PasswordField passwordField;
	private final Window helpWindow;
	
	public LoginViewImpl(EventBus eventBus, AuthenticationService service){
		presenter = new LoginPresenter(this, eventBus, service);
		generateUI();
		this.setSizeFull();
		
		
		helpWindow = new Window("Help");
		helpWindow.setContent(new Label("Enter any arbitary username and password to login"));
		helpWindow.center();
		helpWindow.setModal(true);
		helpWindow.setSizeUndefined();
		
		
	}
	
	
	private void generateUI(){

		VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSpacing(true);
		loginPanel.setWidth("300px");

		Label header = new Label("Please login");
		header.addStyleName("h1");
		loginPanel.addComponent(header);

		usernameField = new TextField("Username");
		usernameField.setWidth("100%");
		loginPanel.addComponent(usernameField);

		passwordField = new PasswordField("Password");
		passwordField.setWidth("100%");
		loginPanel.addComponent(passwordField);

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);
		loginPanel.addComponent(buttons);
		loginPanel.setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);

		loginButton = new Button("login");
		loginButton.setClickShortcut(KeyCode.ENTER);
		loginButton.addStyleName("default");
		loginButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = -2409849771220719930L;

			@Override
			public void buttonClick(ClickEvent event) {
				doLogin();
			}
		});
		
		buttons.addComponent(loginButton);

		clearButton = new Button("clear");
		clearButton	.addStyleName("default");
		clearButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = -5671474518871532339L;

			@Override
			public void buttonClick(ClickEvent event) {
				clearForm();
			}
		});
		
		buttons.addComponent(clearButton);
		
		
		
		Button helpButton = new Button("help");
		helpButton.addStyleName("default");
		helpButton.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -1393725228206638408L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(helpWindow);
			}
			
		});
		
		Resource res = new ThemeResource("../runo/icons/16/help.png");
		helpButton.setIcon(res);
		buttons.addComponent(helpButton);

		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.addComponent(loginPanel);
		mainLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		mainLayout.setSizeFull();
		mainLayout.setHeight("100%");

		usernameField.focus();

		this.addComponent(mainLayout);
	}
	
	@Override
	public void showLoginFailed() {
		enableFields(true);
		Notification.show("Login failed. Please try again.", Notification.Type.HUMANIZED_MESSAGE);
	}
	
	private void doLogin(){
		enableFields(false);
		presenter.doLogin(usernameField.getValue(), passwordField.getValue());
	}

	@Override
	public void clearForm() {
		usernameField.setValue("");
		passwordField.setValue("");
		enableFields(true);
	}

	private void enableFields(boolean e){
		loginButton.setEnabled(e);
		clearButton.setEnabled(e);
		usernameField.setEnabled(e);
		passwordField.setEnabled(e);
	}
	
}
