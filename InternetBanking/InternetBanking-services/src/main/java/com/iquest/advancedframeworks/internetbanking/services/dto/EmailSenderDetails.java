package com.iquest.advancedframeworks.internetbanking.services.dto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:emailSenderDetails.properties")
public class EmailSenderDetails {

  @Autowired
  private Environment environment;

  private String email;
  private String password;

  @PostConstruct
  public void init() {
    email = environment.getProperty("sender.email");
    password = environment.getProperty("sender.password");
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
