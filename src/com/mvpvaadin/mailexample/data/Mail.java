package com.mvpvaadin.mailexample.data;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents an email
 * @author Hannes Dorfmann
 *
 */
public class Mail implements Serializable{
	
	private static final long serialVersionUID = -327820292880169853L;
	
	private String sender;
	private String receiver;
	private String subject;
	private Date date;
	private String message;
	private boolean read;
	
	
	
	
	public Mail(String sender, String receiver, String subject, String message, Date date) {
		this.receiver = receiver;
		this.sender = sender;
		this.date = date;
		this.message = message;
		this.subject = subject;
		read = false;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
}
