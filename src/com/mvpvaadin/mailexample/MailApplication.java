package com.mvpvaadin.mailexample;

import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.event.RefresherGlobalEventBusDispatcher;
import com.mvplite.view.LiteNavigationController;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.login.LoginSuccessfulEvent;
import com.mvpvaadin.mailexample.login.LoginViewImpl;
import com.mvpvaadin.mailexample.login.LogoutEvent;
import com.mvpvaadin.mailexample.main.MainViewImpl;
import com.mvpvaadin.mailexample.main.ShowMainViewEvent;
import com.mvpvaadin.mailexample.service.AuthenticationService;
import com.mvpvaadin.mailexample.service.MailService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("MailTheme")
public class MailApplication extends UI {
	
	private static final long serialVersionUID = -147738545672590891L;
	
	private final EventBus eventBus = new EventBus();
	private final LiteNavigationController navigationController = new LiteNavigationController(eventBus);
	private RefresherGlobalEventBusDispatcher globalDispatcher;
	private LoginViewImpl loginView;
	private MainViewImpl mainView;
	
	private final MailService mailService = MailService.getInstance();
	private final AuthenticationService authenticationService = AuthenticationService.getInstance();
	
	private User authenticatedUser;
	
	@Override
	public void init(VaadinRequest request) {
		bind();
		
		navigationController.setFire404OnUnknownUriFragment(false);
		
		
		// Instantiate LoginView
		loginView = new LoginViewImpl(eventBus, authenticationService);
		
		setContent(loginView);
	}

	
	private void bind(){
		eventBus.addHandler(this);
	}
	

	@EventHandler
	public void onLoginSuccessful(LoginSuccessfulEvent e) {
		this.authenticatedUser = e.getUser();
		showMainView();
		
		String sessionId = getSession().getSession().getId();
		
		globalDispatcher = new RefresherGlobalEventBusDispatcher(
				authenticatedUser.getEmailAddress(), sessionId,
				null, eventBus);
		
		addExtension(globalDispatcher);
		globalDispatcher.start();
	}


	@EventHandler
	public void onLogout(LogoutEvent e) {
		authenticationService.doLogout(e.getUser());
		loginView.clearForm();
		globalDispatcher.stop();
		setContent(loginView);
		this.close();
	}
	
	
	@EventHandler
	public void onShowMainViewRequired(ShowMainViewEvent e) {
		showMainView();
	}
	
	
	private void showMainView(){
		
		if (mainView == null)
			mainView = new MainViewImpl(authenticatedUser, eventBus, navigationController, mailService);
		
		setContent(mainView);

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
