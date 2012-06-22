package com.mvpvaadin.mailexample.statistics;

import com.mvpvaadin.event.Event;
import com.mvpvaadin.event.EventType;


/**
 * This is event is fired, to display the welcome view
 * 
 */
public class ShowStatisticsViewEvent extends Event<ShowStatisticsViewHandler>{
	
	public static final EventType<ShowStatisticsViewHandler> TYPE = new EventType<ShowStatisticsViewHandler>();
	
	public ShowStatisticsViewEvent(){
	}

	@Override
	public EventType<ShowStatisticsViewHandler> getType() {
		return TYPE;
	}

	@Override
	public void dispatch(ShowStatisticsViewHandler handler) {
		handler.onShowStatisticsViewRequired();
	}
	
	
	
}
