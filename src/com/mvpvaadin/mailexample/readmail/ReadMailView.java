package com.mvpvaadin.mailexample.readmail;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.view.SubView;

public interface ReadMailView extends SubView {
	
	public void setMail(Mail mail);

}
