package com.mvpvaadin.mailexample.readmail;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvplite.view.NavigateableSubView;

public interface ReadMailView extends NavigateableSubView {
	
	public void setMail(Mail mail);

}
