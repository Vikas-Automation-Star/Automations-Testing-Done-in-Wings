package com.wings.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;

public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param path
     */

    public static void sendEmail(Session session, String toEmail, String path) {
        try {
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(path);
            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName("./PieChartWithHTMLColors.png");
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            DataSource source1 = new FileDataSource("./PieChartWithHTMLColors.png");
            attachmentBodyPart.setDataHandler(new DataHandler(source1));
            attachmentBodyPart.setFileName("PieChartWithHTMLColors.png");
            multipart.addBodyPart(attachmentBodyPart);


            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("productupdates@wingsinfo.net", "NoReply-QA"));
            //msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject("Sales Invoices Test Cases", "UTF-8");
            //Wings Test Automation Reports
            //msg.setText(body, "UTF-8");
            msg.setContent(multipart);

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}