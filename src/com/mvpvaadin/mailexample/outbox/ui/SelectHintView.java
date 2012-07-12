package com.mvpvaadin.mailexample.outbox.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class SelectHintView extends HorizontalLayout {

	private static final long serialVersionUID = -7229315793137050106L;
	private Label l;

	public SelectHintView(String positionDescription){
		
		this.setSizeFull();
		l = new Label("<center>Select an element from the list on the "+positionDescription+" </center>", Label.CONTENT_XHTML); 
		l.setStyleName("h2");
		this.addComponent(l);
		this.setComponentAlignment(l, Alignment.MIDDLE_CENTER);
	}
	
	
	
	public void setHintText(String text){
		l.setCaption(text);
	}
	
}
