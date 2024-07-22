package com.wings.driverManager;

import javax.mail.*;
import javax.mail.internet.*;

public class MailSun {



    public static void main(String args[]) throws Exception {
        // Set up the SMTP server.
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        String to = "manoj.c@wingsinfo.net";
        String from = "QA@wingsinfo.net";
        String subject = "Hello";
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText("Hi,\n\nHow are you?");

            // Send the message.
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
