package com.infoshareacademy.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Stateless
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class.getName());

    private final String user = getEmailProperties("user");
    private final String pass = getEmailProperties("pass");
    private final String dest = getEmailProperties("dest");


    public void sendEmail(String htmlMessage) {
        Session session = Session.getInstance(getEmailConfigProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));
            message.setSubject("Recipes approval notification");
            message.setContent(htmlMessage, "text/html; charset=utf-8");



/*
            message.setText("DearAdmin,"
                    + "\n\n There are drink recipes awaiting your approval:");*/
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Error during sending email, {}", e.getMessage());
        }

    }

    private Properties getEmailConfigProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("emailConfig.properties"))
                    .openStream());
        } catch (IOException e) {
            LOGGER.error("Error during loading email config properties, {}", e.getMessage());
        }
        return properties;
    }


    private String getEmailProperties(String key) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("email.properties"))
                    .openStream());
        } catch (IOException e) {
            LOGGER.error("Error during loading email properties, {}", e.getMessage());
        }
        return properties.getProperty(key);
    }

}