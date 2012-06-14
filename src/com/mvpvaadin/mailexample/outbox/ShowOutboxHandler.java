package com.mvpvaadin.mailexample.outbox;

import com.mvpvaadin.event.EventHandler;

public interface ShowOutboxHandler extends EventHandler{

	public void onShowOutboxRequired();
}
