package com.mvpvaadin.mailexample.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;

public class MailService {
	
	private static MailService INSTANCE = new MailService();
	
	private Map<Integer, List<Mail> > inboxMailStorage;
	private Map<Integer, List<Mail>> outboxMailStorage;
	
	private String [] emails = {"tom@mail.com", "daniel@mail.com", "monica@mail.com", "simon@mail.com", "amy@mail.com", "maria@mail.com"};
	private String [] subjects = {"Meeting", "Dinner", "Launch", "Hello", "Party", "Spam"};
	private String msgBody = "Hello, this is a simple message body text.";
	
	
	private MailService(){
		
		inboxMailStorage = new ConcurrentHashMap<Integer, List<Mail>>();
		outboxMailStorage = new ConcurrentHashMap<Integer, List<Mail>>();
	}
	
	
	public static MailService getInstance(){
		return INSTANCE;
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

		List<Mail> mails = inboxMailStorage.get(user.getId());
		
		if (mails == null)
		{
			mails = generateRandomMails(user, true);
			inboxMailStorage.put(user.getId(), mails);
		}
		
		return mails;
	}
	
	
	
	
	/**
	 * Get all (random generated) {@link Mail}s of an user.
	 * @param user
	 * @return {@link List} of all {@link Mail}s of a certain User
	 */
	public List<Mail> getOutboxMailsOf(User user){

		List<Mail> mails = outboxMailStorage.get(user.getId());
		
		if (mails == null)
		{
			mails = generateRandomMails(user, false);
			outboxMailStorage.put(user.getId(), mails);
		}
		
		return mails;
	}
	
	
	/**
	 * Simulates sending a {@link Mail} by adding this {@link Mail} to the users outbox
	 * @param sender
	 * @param mail
	 */
	public void sendMail(User sender, Mail mail){
		
		List<Mail> mails = getOutboxMailsOf(sender);
		mails.add(mail);
		
	}
	
	
	/**
	 * Delete all associated {@link Mail}s of a {@link User}.
	 * Normally this method is called, after a user has been logged out.
	 * @param user
	 */
	public void deleteAllMailsOf(User user){
		inboxMailStorage.remove(user.getId());
		outboxMailStorage.remove(user.getId());
	}
	

}
