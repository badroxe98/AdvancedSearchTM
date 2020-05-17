package org.sid.mailSender;

import java.util.Properties;

import org.sid.entities.Client;
import org.sid.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
@Service
public class NotificationService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender)  {
		this.javaMailSender=javaMailSender;
	}
	public void sendNotification(Client client, String msg) throws MailException{
		
		
		final String username = "ghikkprojet@gmail.com";
        final String password = "ghikkghikk";
        final String host= "smtp.gmail.com";
        final int port=587;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
 
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(client.getEmail()));
            message.setSubject("Welcome to Training Management");
            message.setContent(msg,"text/html");
           
            Transport transport = session.getTransport("smtp");
            transport.connect (host, port,username,password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();  
 
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
	}
	
	
public void sendNotificationIfArticleRemoved(List<String> emails,Formation formation) throws MailException{
		
		
		final String username = "ghikkprojet@gmail.com";
        final String password = "ghikkghikk";
        final String host= "smtp.gmail.com";
        final int port=587;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
 
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
 
        try {
        	for(int i=0;i<emails.size();i++) {
        		String msg="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
        	            "<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi dear customer</h1>"+
        	            		"<p>" + 
        	            		"The training entitled <strong>"+formation.getTitle()+"</strong> has been canceled by its owner <strong>"+formation.getUser().getNom()+" "+formation.getUser().getPrenom()+".</strong>"+
        	            		"</p>"+
        	            		"<p>Thank you and see you soon.</p></div></div>";
        		Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emails.get(i)));
                message.setSubject("Welcome to Training Management");
                message.setContent(msg,"text/html");
               
                Transport transport = session.getTransport("smtp");
                transport.connect (host, port,username,password);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close(); 
        	}
             
 
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
	}
}
