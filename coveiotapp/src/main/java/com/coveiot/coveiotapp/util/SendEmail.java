package com.coveiot.coveiotapp.util;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;


import javax.activation.*;



public class SendEmail
{
	 static ConfigReader config = ConfigReader.getInstance();
	// public static void sentMailMessageTo(String OrderId, String i8Number, String subject, String Email_Body,String[] recipient){
	 public static void sentMailMessageTo(String[] recipient, String subject, String Email_Body){
		 //  String recipients = config.email_toRecipients;
		   // String fromEmailAdr = config.email_fromEmailAddress;
		
	       Address addresses[] = new Address[recipient.length];
     for (int i = 0; i < recipient.length; i++) {
         try {
             addresses[i] = new InternetAddress(recipient[i]);
         } catch (AddressException ex) {
             System.out.println(ex);
            
         }
     }
			Properties props = new Properties();
			props.put("mail.smtp.host", config.emailHost);
			props.put("mail.smtp.socketFactory.port", config.email_port);
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");			
			props.put("mail.smtp.port", config.email_port);
			Session session;
			  if (config.email_authRequired.equalsIgnoreCase("true")) {
			session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication( config.email_authUsername,
								config.email_authPassword);
					}
				});
			
			props.put("mail.smtp.auth", "true");
			 			  
	   } else {
      session = Session.getDefaultInstance(props, null);
  }
		 
  try {
    
     // ConfigReader config = ConfigReader.getInstance();
		 
 		String hostserver = config.emailHost;
 		props.put("mail.smtp.host", hostserver);
      Message msg = new MimeMessage(session);
      InternetAddress addressFrom = new InternetAddress(config.email_authUsername);
      msg.setFrom(addressFrom);
      msg.setRecipients(Message.RecipientType.TO, addresses);
      msg.setSubject(subject);

      MimeMultipart ct = new MimeMultipart();

      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(getHtml(Email_Body));
     // message.setContent(body, "text/html; charset=utf-8");
      ct.addBodyPart(mbp1);
      msg.setContent(Email_Body,"text/html; charset=utf-8");
   //   msg.setContent(ct);
      Transport.send(msg);
      //For log-file
      String sl = "Message:\n";
      sl += "Subject: " + subject + "\n";
      sl += Email_Body + "\n";
      sl += "============================================================";
      System.out.println(sl);

  } catch (javax.mail.MessagingException ex) {
      System.out.println(ex);
     
  }

}
	 
	    private static String getHtml(String s) {
	        String as = "<html>";
	        String[] sp = s.split("\n");
	        for (String es : sp) {
	            as += es + "<br>";
	        }
	        as += "</html>";
	        return as;
	    }
	public static void sendEmail(String fromAddr,String toAddr,String subject,String body){
		// Recipient's email ID needs to be mentioned.
	      String to = toAddr;

	      // Sender's email ID needs to be mentioned
	      String from = fromAddr;

	      // Assuming you are sending email from localhost
	      String host = config.emailHost;

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      // Setup mail server port 
	      /* Uncomment for NCS Setup */
	      //String port = ConfigReader.emailPort;
	      
	
	      properties.put("mail.smtp.socketFactory.port", "465");
 
	      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         
	         // Set CC
	         message.addRecipient(Message.RecipientType.CC,
                     new InternetAddress(from));
	         
	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
	         //	message.setText(body);
	         message.setContent(body, "text/html; charset=utf-8");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
