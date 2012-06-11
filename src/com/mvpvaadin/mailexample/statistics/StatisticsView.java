package com.mvpvaadin.mailexample.statistics;

import com.mvpvaadin.view.SubView;

public interface StatisticsView extends SubView{
	
	public void setUnreadMailsCount(int unread);
	public void setUsername(String username);
	public void setEmailAddress(String emailAddress);
	public void setOutboxMailCount(int mails);

}
