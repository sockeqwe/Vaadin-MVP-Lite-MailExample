package com.mvpvaadin.mailexample.service;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;

public class MailService  implements Serializable{
	
	private static final long serialVersionUID = 4311641059640246505L;
	
	private List<Mail> inboxMailStorage;
	private List<Mail> outboxMailStorage;
	
	private String [] emails = {"tom@mail.com", "daniel@mail.com", "monica@mail.com", "simon@mail.com", "amy@mail.com", "maria@mail.com"};
	private String [] subjects = {"Meeting", "Dinner", "Launch", "Hello", "Party", "Spam"};
	private String msgBody = "Hello, this is a simple message body text.";
	
	
	public MailService(){
		
	}

	/**
	 * Generate mails
	 * @param user
	 * @param inbox is this mail a incoming ( = true) or outgoing ( = false) mail
	 * @return
	 */
	private List<Mail> generateRandomMails(User user, boolean inbox){
		
		List<Mail> mails = new LinkedList<Mail>();
		Random rand = new Random();
		
		for (int i =0; i<19; i++)
		{
			String sender;
			if (inbox) 
				sender = emails[rand.nextInt(emails.length)];
			else
				sender = user.getEmailAddress();
			
			String receiver;
			if (inbox) 
				receiver = user.getEmailAddress();
			else
				receiver = emails[rand.nextInt(emails.length)];
			
			String subject = subjects[rand.nextInt(subjects.length)];
			Date date = new Date();
			date.setTime(date.getTime()-rand.nextInt(1209600000));
			Mail m = new Mail(sender, receiver, subject, msgBody, date);
			m.setId(i);
			mails.add(m);
		}
		
		
		return mails;
	}
	
	
	/**
	 * Get the count of unread {@link Mail}s
	 * @param user
	 * @return
	 */
	public int getUnreadInboxCountOf(User user){
		
		List<Mail> mails = getInboxMailsOf(user);
		
		int unread = 0;
		
		for (Mail m: mails)
			if (!m.isRead())
				unread++;
		
		return unread;
	}
	
	
	/**
	 * Get the number of {@link Mail}s in the users outbox
	 * @param user
	 * @return
	 */
	public int getOutboxCountOf(User user){
		return getOutboxMailsOf(user).size();
	}
	
	
	/**
	 * Get all (random generated) {@link Mail}s of an user.
	 * @param user
	 * @return {@link List} of all {@link Mail}s of a certain User
	 */
	public List<Mail> getInboxMailsOf(User user){

		
		if (inboxMailStorage == null)
		{
			inboxMailStorage = generateRandomMails(user, true);
			
		}
		
		return inboxMailStorage;
	}
	
	
	
	
	/**
	 * Get all (random generated) {@link Mail}s of an user.
	 * @param user
	 * @return {@link List} of all {@link Mail}s of a certain User
	 */
	public List<Mail> getOutboxMailsOf(User user){

		
		if (outboxMailStorage == null)
		{
			outboxMailStorage = generateRandomMails(user, false);
		}
		
		return outboxMailStorage;
	}
	
	
	/**
	 * Simulates sending a {@link Mail} by adding this {@link Mail} to the users outbox
	 * @param sender
	 * @param mail
	 */
	public void sendMail(User sender, Mail mail){
		outboxMailStorage.add(mail);	
	}
	
	
	/**
	 * Delete all associated {@link Mail}s of a {@link User}.
	 * Normally this method is called, after a user has been logged out.
	 * @param user
	 */
	public void deleteAllMailsOf(User user){
		inboxMailStorage.clear();
		outboxMailStorage.clear();
	}
	

}
