package com.wings.driverManager;

import java.io.FileInputStream;

import java.util.Properties;



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






public class Mail {











    public static void consolidatedmailresult() throws Exception{





        String userName="QA@wingsinfo.net";

        String passWord= "TestAutomation@19";

        String host="smtp.office365.com";

        String port="587";

        String starttls="true";

        String auth="true";

        boolean debug=true;

        String socketFactoryClass="javax.net.ssl.SSLSocketFactory";

        String fallback="false";


        String to="manoj.c@winginfo.net";

//		String cc ="";

        String subject="Test Execution Report";

        String attachmentPath= "";



        {

            //Object Instantiation of a properties file.

            Properties props = new Properties();

            props.put("mail.smtp.user", userName);

            props.put("mail.smtp.host", host);



            if(!"".equals(port)){

                props.put("mail.smtp.port", port);

            }



            if(!"".equals(starttls)){

                props.put("mail.smtp.starttls.enable",starttls);

                props.put("mail.smtp.auth", auth);

            }



            if(debug){

                props.put("mail.smtp.debug", "true");

            }

            else{

                props.put("mail.smtp.debug", "false");

            }



            if(!"".equals(port)){

                props.put("mail.smtp.socketFactory.port", port);
                System.out.println("Port details filled");

            }



            if(!"".equals(socketFactoryClass)){

                props.put("mail.smtp.socketFactory.class",socketFactoryClass);

            }



            if(!"".equals(fallback)){

                props.put("mail.smtp.socketFactory.fallback", fallback);

            }



            try{



                Session session = Session.getDefaultInstance(props, null);



                //session.setDebug(debug);



                MimeMessage msg = new MimeMessage(session);

                msg.setSubject(subject);



                Multipart multipart = new MimeMultipart();

                MimeBodyPart messageBodyPart = new MimeBodyPart();

                DataSource source = new FileDataSource(attachmentPath);

                messageBodyPart.setDataHandler(new DataHandler(source));

                //messageBodyPart.setFileName(attachmentName);

                multipart.addBodyPart(messageBodyPart);



                msg.setContent(multipart);

                msg.setFrom(new InternetAddress(userName));



                msg.addRecipients(Message.RecipientType.TO, to);

//				msg.addRecipients(Message.RecipientType.CC, cc);

//				msg.addRecipients(Message.RecipientType.TO, bcc);

                msg.saveChanges();



                Transport transport = session.getTransport("smtps");
                //transport.

                transport.connect(host, userName, passWord);

                transport.sendMessage(msg, msg.getAllRecipients());



                transport.close();


                System.out.println("Mail Sent");

            }



            catch (Exception e){

                e.printStackTrace();

            }

        }

    }

    public static void main(String args[]) throws Exception

    {

        consolidatedmailresult();

    }



}


