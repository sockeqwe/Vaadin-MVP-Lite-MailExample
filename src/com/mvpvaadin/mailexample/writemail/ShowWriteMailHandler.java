package com.mvpvaadin.mailexample.writemail;

import com.mvplite.event.ShowViewEventHandler;

public interface ShowWriteMailHandler extends ShowViewEventHandler{

	public void onShowWriteMailViewRequired(String receiverMailAddress, String subject);
}
