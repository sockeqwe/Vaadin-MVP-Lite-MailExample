package com.mvpvaadin.mailexample.data;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 265816562494351007L;
	
	private int id;
	private String username;
	
	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getEmailAddress(){
		return username+"@mail-example.com";
	}

}
