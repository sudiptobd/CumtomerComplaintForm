/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

// File Name SendFileEmail.java

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail {
    
    //public static int status = 1 ;
    public void send(String from,String password,String to,String sub,String msg , File file){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           //message.setText(msg);
           
           
           
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText(msg);
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         
           if (file != null)
           {
               messageBodyPart = new MimeBodyPart();
               messageBodyPart.setFileName(file.getName());
                DataSource source = new FileDataSource(file);
                messageBodyPart.setDataHandler(new DataHandler(source));
                multipart.addBodyPart(messageBodyPart);
                
           }
           message.setContent(multipart);
           //send message  
           //status = 3 
           Transport.send(message);
           
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  

   
}