package com.mvpvaadin.mailexample.outbox;

import com.mvplite.event.ShowViewEventHandler;

public interface ShowOutboxHandler extends ShowViewEventHandler{

	public void onShowOutboxRequired();
}
