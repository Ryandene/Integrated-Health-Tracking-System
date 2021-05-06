package com.perisic.beds.rmiinterface;

import java.io.IOException;
import java.rmi.Naming;
import java.util.Date;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AnalyseQuestionsImplementation implements IAnalyzeQuestions{

	@Override
	public void sendEmailWithAnalayzedAttachments(String recepient, String fromAddress, String frompwd, String subject, String body, String attachments[]) throws AddressException, MessagingException, AuthenticationFailedException {
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", fromAddress);
		properties.put("mail.password", frompwd);
		
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromAddress, frompwd);
			}
		};		
		
		Session session = Session.getInstance(properties, authenticator);

		Message preparedMessage = new MimeMessage(session);

		preparedMessage.setFrom(new InternetAddress(fromAddress));
		InternetAddress[] recepients = { new InternetAddress(recepient) };
		preparedMessage.setRecipients(Message.RecipientType.TO, recepients);
		preparedMessage.setSubject("Adult Health Checker | " + subject);
		preparedMessage.setSentDate(new Date());

		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(body, "text/html");

		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);		
		
		if (attachments != null && attachments.length > 0) {
            for (String filePath : attachments) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
		
		preparedMessage.setContent(multipart);
		Transport.send(preparedMessage);
	}
	
}
