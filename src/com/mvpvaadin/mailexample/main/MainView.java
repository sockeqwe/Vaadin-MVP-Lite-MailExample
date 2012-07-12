package com.mvpvaadin.mailexample.main;

import com.mvplite.view.NavigateableView;

public interface MainView extends NavigateableView {
	
	public void setInboxUnreadCount(int unread);
	public void showStartSubView();
	

}
