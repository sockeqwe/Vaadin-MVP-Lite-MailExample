package com.mvpvaadin.mailexample;

import com.mvpvaadin.event.EventBus;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.login.LoginSuccessfulEvent;
import com.mvpvaadin.mailexample.login.LoginSuccessfulHandler;
import com.mvpvaadin.mailexample.login.LoginViewImpl;
import com.mvpvaadin.mailexample.login.LogoutEvent;
import com.mvpvaadin.mailexample.login.LogoutHandler;
import com.mvpvaadin.mailexample.main.MainViewImpl;
import com.mvpvaadin.mailexample.main.ShowMainViewEvent;
import com.mvpvaadin.mailexample.main.ShowMainViewHandler;
import com.mvpvaadin.mailexample.service.AuthenticationService;
import com.mvpvaadin.view.NavigationController;
import com.vaadin.Application;
import com.vaadin.ui.Window;

public class MailApplication extends Application implements LoginSuccessfulHandler, LogoutHandler, ShowMainViewHandler{
	
	private static final long serialVersionUID = -147738545672590891L;
	
	private EventBus eventBus = new EventBus();
	private NavigationController navigationController = new NavigationController(eventBus);
	
	private LoginViewImpl loginView;
	private Window mainWindow;
	private MainViewImpl mainView;
	
	private User authenticatedUser;
	
	@Override
	public void init() {
		bind();
		setTheme("MailTheme");
		
		
		// Instantiate LoginView
		loginView = new LoginViewImpl(eventBus);
		
		setMainWindow(loginView);
	}

	
	private void bind(){
		eventBus.addHandler(LoginSuccessfulEvent.TYPE, this);
		eventBus.addHandler(LogoutEvent.TYPE, this);
		eventBus.addHandler(ShowMainViewEvent.TYPE, this);
	}
	

	public void onLoginSuccessful(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
		showMainView();
	}


	public void onLogout(User user) {
		
		navigationController.clearUriFragments();
		
		AuthenticationService.getInstance().doLogout(user);
		loginView.clearForm();
		removeWindow(mainWindow);
		setMainWindow(loginView);
		
		reloadPage();
	}

	
	private void showMainView(){
		
		if (mainView == null)
			mainView = new MainViewImpl(authenticatedUser, eventBus, navigationController);
		
		
		if (mainWindow == null)
		{	mainWindow = new Window("Mail Application", mainView);
			mainWindow.addComponent(navigationController);
			
		}
		
		removeWindow(getMainWindow());
		setMainWindow(mainWindow);

		mainView.showStartSubView();
		
	}
	

	public void onShowMainViewRequired() {
		showMainView();
	}
	
	
	
	/**
	 * reloads the page with removing all "#" tags
	 */
	private void reloadPage(){
		String js = "var url = location.href.split(\"#\")[0];" +
				"window.location.href = url;";
		
		getMainWindow().executeJavaScript(js);
	}

}
