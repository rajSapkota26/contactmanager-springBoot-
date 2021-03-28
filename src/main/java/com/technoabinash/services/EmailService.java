package com.technoabinash.services;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;


@Service
public class EmailService {

	public boolean sendEmail(String subject,String message,String to) {
		boolean f=false;
		//sender gmail
		String from="spabinash2626@gmail.com";
		//gmail host
		String host="smtp.gmail.com";
		
		//get system property
		Properties properties=System.getProperties();
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//now get the sesssion object
		Session session=Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("spabinash2626@gmail.com", "spabinashsapkota9846440376");
			}
			
		});
		session.setDebug(true);
		
		//set message
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			//message sender
			mimeMessage.setFrom(from);
			
			//message receiver
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//set subject
			mimeMessage.setSubject(subject);
			//set message
			mimeMessage.setText(message);
			
			//now send email using transport class
			Transport.send(mimeMessage);
			System.out.println("send success...");
			f=true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
		
	}
}
