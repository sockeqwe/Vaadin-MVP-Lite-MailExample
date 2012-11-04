package com.mvpvaadin.mailexample.service;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.mvplite.event.GlobalEventBus;
import com.mvpvaadin.mailexample.data.Mail;
import com.mvpvaadin.mailexample.data.User;
import com.mvpvaadin.mailexample.service.event.MailReceivedEvent;

public class MailService  implements Serializable{
	
	private static final long serialVersionUID = 4311641059640246505L;
	
	/**
	 * Singleton Pattern
	 */
	private static MailService INSTANCE = new MailService();
	
	public static final String MAIL_DOMAIN = "mail.com";

	private final Map<User, List<Mail>> inboxMailStorage;
	private final Map<User, List<Mail>> outboxMailStorage;
	
	private final String [] emails = {"tom@other-mail.com", "daniel@other-mail.com", "monica@other-mail.com", "simon@other-mail.com", "amy@other-mail.com", "maria@other-mail.com"};
	private final String [] subjects = {"Meeting", "Dinner", "Launch", "Hello", "Party", "Spam"};
	private final String msgBody = "Hello, this is a simple message body text.";
	
	
	private MailService(){ 
		inboxMailStorage = new ConcurrentHashMap<User, List<Mail>>();
		outboxMailStorage = new ConcurrentHashMap<User, List<Mail>>();
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
		initInbox(user);
		return inboxMailStorage.get(user);
	}
	
	
	/**
	 * Initializes the Inbox with random mails, if the inbox is not already initialized
	 */
	private void initInbox(User user){
		List<Mail> mails = inboxMailStorage.get(user);
		
		if (mails == null)
		{
			mails = generateRandomMails(user, true);
			inboxMailStorage.put(user, mails);
		}
	}
	
	/**
	 * Initializes the Outbox with random mails, if the users outbox is not already initialized
	 */
	private void initOutbox(User user){
		List<Mail> mails = outboxMailStorage.get(user);
		
		if (mails == null)
		{
			mails = generateRandomMails(user, false);
			outboxMailStorage.put(user, mails);
		}
	}
	
	/**
	 * Get all (random generated) {@link Mail}s of an user.
	 * @param user
	 * @return {@link List} of all {@link Mail}s of a certain User
	 */
	public List<Mail> getOutboxMailsOf(User user){
		initOutbox(user);
		return outboxMailStorage.get(user);
	}
	
	
	/**
	 * Simulates sending a {@link Mail} by adding this {@link Mail} to the users outbox
	 * @param sender
	 * @param mail
	 */
	public void sendMail(User sender, Mail mail){
		initOutbox(sender);
		List<Mail>mails = outboxMailStorage.get(sender);
		mails.add(mail);
		
		
		// Check if the receiver is a user in the system
		
		User receiver = AuthenticationService.getInstance().isUser(mail.getReceiver());
		if (receiver != null)
		{
			initInbox(receiver);
			inboxMailStorage.get(receiver).add(mail);
			// Fire Event to inform the receiver, that he has received a new mail
			GlobalEventBus.fireEventToUser(mail.getReceiver(), new MailReceivedEvent(mail));
		}
		
		
	}
	
	
	/**
	 * Delete all associated {@link Mail}s of a {@link User}.
	 * Normally this method is called, after a user has been logged out.
	 * @param user
	 */
	public void deleteAllMailsOf(User user){
		inboxMailStorage.remove(user).clear();
		outboxMailStorage.remove(user).clear();
	}
	

}
