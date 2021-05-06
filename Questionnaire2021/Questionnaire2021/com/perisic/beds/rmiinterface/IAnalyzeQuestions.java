package com.perisic.beds.rmiinterface;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IAnalyzeQuestions {
	
	public void sendEmailWithAnalayzedAttachments(String toAddress, String fromAddress, String frompwd, String subject, String body, String attachments[]) throws AddressException, MessagingException, AuthenticationFailedException;
	
}
