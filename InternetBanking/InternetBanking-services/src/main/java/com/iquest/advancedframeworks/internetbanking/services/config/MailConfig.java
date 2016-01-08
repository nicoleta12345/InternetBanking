package com.iquest.advancedframeworks.internetbanking.services.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {

  public JavaMailSenderImpl javaMailSender() {
    Properties prop = new Properties();
    InputStream input = null;
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    try {
      input = MailConfig.class.getClassLoader().getResourceAsStream("mailConfig.properties");
      prop.load(input);

      mailSender.setHost(prop.getProperty("email.host"));
      mailSender.setPort(Integer.parseInt(prop.getProperty("email.port")));
      mailSender.setUsername(prop.getProperty("email.username"));
      mailSender.setPassword(prop.getProperty("email.password"));
      mailSender.setJavaMailProperties(getMailProperties());      
    } catch (IOException ex) {
      //stay silent 
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          //stay silent
        }
      }
    }

    return mailSender;
  }

  private Properties getMailProperties() {
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.setProperty("mail.debug", "true");
    return properties;
  }
  
}