package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.iquest.advancedframeworks.internetbanking.services.config.MailConfig;

/**
 * The MailSenderService class sends mails to custom mails with custom messages.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class MailSenderService {

  /**
   * The configuration for the JavaMailSender object.
   */
  private MailConfig mailConfig;

  /**
   * The JavaMAilSender object sends the mails.
   */
  private JavaMailSender mailSender;

  /**
   * Default constructor. Initialize the JavaMailSender object used to send mails.
   */
  public MailSenderService() {
    mailConfig = new MailConfig();
    mailSender = mailConfig.javaMailSender();
  }

  /**
   * Sends a mail to a mail address received as parameter with a custom subject and message also received as parameters.
   * 
   * @param to the receiver mail address
   * @param subject the subject of the mail
   * @param msg the message of the mail
   */
  public void sendMail(String to, String subject, String msg) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(msg);

    mailSender.send(message);
  }

}