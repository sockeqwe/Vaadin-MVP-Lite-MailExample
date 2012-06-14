package com.mvpvaadin.mailexample.outbox;

import java.util.List;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.view.NavigateableView;

public interface OutboxView extends NavigateableView {

	public void setMails(List<Mail> mails);
}
