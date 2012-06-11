package com.mvpvaadin.mailexample.login;

import com.mvpvaadin.view.View;

public interface LoginView extends View{
	
	public void showLoginFailed();
	
	public void clearForm();
	
}
