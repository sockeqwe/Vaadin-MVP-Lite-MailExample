package com.mvpvaadin.mailexample;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.view.LiteNavigationController;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.login.LoginSuccessfulEvent;
import com.mvpvaadin.mailexample.login.LoginViewImpl;
import com.mvpvaadin.mailexample.login.LogoutEvent;
import com.mvpvaadin.mailexample.main.MainViewImpl;
import com.mvpvaadin.mailexample.main.ShowMainViewEvent;
import com.mvpvaadin.mailexample.service.AuthenticationService;
import com.mvpvaadin.mailexample.service.MailService;
import com.vaadin.Application;
import com.vaadin.ui.Window;

public class MailApplication extends Application {
	
	private static final long serialVersionUID = -147738545672590891L;
	
	private final EventBus eventBus = new EventBus();
	private final LiteNavigationController navigationController = new LiteNavigationController(eventBus);
	
	private LoginViewImpl loginView;
	private Window mainWindow;
	private MainViewImpl mainView;
	
	private final MailService mailService = new MailService();
	private final AuthenticationService authenticationService = new AuthenticationService();
	
	private User authenticatedUser;
	
	@Override
	public void init() {
		bind();
		setTheme("MailTheme");
		navigationController.setFire404OnUnknownUriFragment(false);
		
		
		// Instantiate LoginView
		loginView = new LoginViewImpl(eventBus, authenticationService);
		
		setMainWindow(loginView);
	}

	
	private void bind(){
		eventBus.addHandler(this);
	}
	

	@EventHandler
	public void onLoginSuccessful(LoginSuccessfulEvent e) {
		this.authenticatedUser = e.getUser();
		showMainView();
	}


	@EventHandler
	public void onLogout(LogoutEvent e) {
		
		//navigationController.clearUriFragments();
		
		authenticationService.doLogout(e.getUser());
		loginView.clearForm();
		removeWindow(mainWindow);
		setMainWindow(loginView);
		this.close();
	}
	
	
	@EventHandler
	public void onShowMainViewRequired(ShowMainViewEvent e) {
		showMainView();
	}
	
	
	private void showMainView(){
		
		if (mainView == null)
			mainView = new MainViewImpl(authenticatedUser, eventBus, navigationController, mailService);
		
		
		if (mainWindow == null)
		{	mainWindow = new Window("Mail Application", mainView);
			mainWindow.addComponent(navigationController);
			
		}
		
		removeWindow(getMainWindow());
		setMainWindow(mainWindow);

		mainView.showStartSubView();
		
	}
	

	
	
	
	
	/**
	 * reloads the page with removing all "#" tags
	
	private void reloadPage(){
		String js = "var url = location.href.split(\"#\")[0];" +
				"window.location.href = url;";
		
		getMainWindow().executeJavaScript(js);
	}
 */
}
