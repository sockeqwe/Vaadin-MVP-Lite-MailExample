package com.mvpvaadin.mailexample.readmail;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.view.NavigateableSubView;

public interface ReadMailView extends NavigateableSubView {
	
	public void setMail(Mail mail);

}
