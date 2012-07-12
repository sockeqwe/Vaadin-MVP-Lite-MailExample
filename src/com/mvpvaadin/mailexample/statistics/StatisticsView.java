package com.mvpvaadin.mailexample.statistics;

import com.mvplite.view.NavigateableSubView;

public interface StatisticsView extends NavigateableSubView{
	
	public void setUnreadMailsCount(int unread);
	public void setUsername(String username);
	public void setEmailAddress(String emailAddress);
	public void setOutboxMailCount(int mails);

}
