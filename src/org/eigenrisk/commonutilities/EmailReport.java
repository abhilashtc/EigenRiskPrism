package org.eigenrisk.commonutilities;
import java.io.IOException;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailReport {

public static void main(String[] args) throws IOException {

    final String username = "eigen.risk.qa@gmail.com";
    final String password = "EigenRisk@123";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
//    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.port", "465");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("abhilashtc@eigenrisk.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("abhilashtc@eigenrisk.com"));
        message.setSubject("Testing Subject");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "C:\\EigenRisk_Prism_Project\\EigenRiskPrism\\test-output\\";
        String fileName = "emailable-report.html";
        DataSource source = new FileDataSource(file);
        //messageBodyPart.setDataHandler(new DataHandler(source));
        //messageBodyPart.setFileName(fileName);
        //multipart.addBodyPart(messageBodyPart);

        //message.setContent(multipart);

        System.out.println("Sending");
        System.out.println(message.getFrom());
        
        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
}