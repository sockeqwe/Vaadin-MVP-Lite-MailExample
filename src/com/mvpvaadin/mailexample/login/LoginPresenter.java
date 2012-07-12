package com.mvpvaadin.mailexample.login;

import java.io.Serializable;

import com.mvplite.event.EventBus;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.AuthenticationService;
import com.mvplite.presenter.Presenter;

public class LoginPresenter extends Presenter<LoginView> implements Serializable{

	private static final long serialVersionUID = 8319039057948560820L;
	
	private AuthenticationService authenticationService;
	
	public LoginPresenter(LoginView view, EventBus eventBus, AuthenticationService service) {
		super(view, eventBus);
		this.authenticationService = service;
	}
	
	
	public void doLogin(String username, String password){
		User user = authenticationService.doLogin(username, password);
		
		if (user == null)
			getView().showLoginFailed();
		else
			getEventBus().fireEvent(new LoginSuccessfulEvent(user));
	}

}
