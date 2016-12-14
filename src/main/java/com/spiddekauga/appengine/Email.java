package com.spiddekauga.appengine;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Email helper tools
 */
public class Email {
/**
 * Send an email
 * @param fromAddress sender of the email
 * @param toEmail who to send the email to
 * @param subject title subject of the email
 * @param content HTML content of the email
 */
public static void sendEmail(InternetAddress fromAddress, String toEmail, String subject, String content) {
	Session session = Session.getDefaultInstance(new Properties());
	MimeMessage message = new MimeMessage(session);

	try {
		message.setFrom(fromAddress);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject(subject);
		message.setContent(content, "text/html");
		Transport.send(message);
	} catch (MessagingException e) {
		e.printStackTrace();
	}
}
}
