package com.iquest.advancedframeworks.internetbanking.integration.jms.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailRequest {

  @XmlElement(name = "EMAIL", required = true)
  private String email;

  @XmlElement(name = "SENDER_EMAIL", required = true)
  private String senderEmail;

  @XmlElement(name = "SUBJECT", required = true)
  private String subject;

  @XmlElement(name = "BODY", required = true)
  private String body;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenderEmail() {
    return senderEmail;
  }

  public void setSenderEmail(String senderEmail) {
    this.senderEmail = senderEmail;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

}
