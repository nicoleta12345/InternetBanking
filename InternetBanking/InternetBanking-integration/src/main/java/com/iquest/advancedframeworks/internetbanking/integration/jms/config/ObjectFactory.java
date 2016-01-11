package com.iquest.advancedframeworks.internetbanking.integration.jms.config;

import javax.xml.bind.annotation.XmlRegistry;

import com.iquest.advancedframeworks.internetbanking.integration.jms.model.request.EmailRequest;
import com.iquest.advancedframeworks.internetbanking.integration.jms.model.response.EmailResponse;

@XmlRegistry
public class ObjectFactory {

  /**
   * Creates a new EmailRequest object.
   * 
   * @return a new EmailRequest object
   */
  public EmailRequest createEmailRequest() {
    return new EmailRequest();
  }

  /**
   * Creates a new EmailResponse object.
   * 
   * @return a new EmailResponse object
   */
  public EmailResponse createEmailResponse() {
    return new EmailResponse();
  }
  
}
