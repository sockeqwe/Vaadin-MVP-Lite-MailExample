package com.mvpvaadin.mailexample.writemail;

import com.mvpvaadin.view.View;

public interface WriteMailView extends View{
	public void showSuccessfulMessage();
	public void showErrorMessage(String reason);
	public void clearForm();
	public void setReceiverMailAddress(String mailAddress);
	public void setSubject(String subject);
}
