package com.mvpvaadin.mailexample.writemail;

import com.mvpvaadin.event.EventHandler;

public interface ShowWriteMailHandler extends EventHandler{

	public void onShowWriteMailViewRequired(String receiverMailAddress, String subject);
}
