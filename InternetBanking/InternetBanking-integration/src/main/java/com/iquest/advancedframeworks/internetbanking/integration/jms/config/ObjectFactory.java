package com.iquest.advancedframeworks.internetbanking.integration.jms.config;



import javax.xml.bind.annotation.XmlRegistry;

import com.iquest.advancedframeworks.internetbanking.integration.jms.model.request.EmailRequest;
import com.iquest.advancedframeworks.internetbanking.integration.jms.model.response.EmailResponse;

/**
 * Factory for creating the e-Portal customer request.
 */
@XmlRegistry
public class ObjectFactory {
  /**
   * The base constructor.
   *
   * @return The {@link EmailRequest}
   */
  public EmailRequest createEmailRequest() {
    return new EmailRequest();
  }

  /**
   * Method used to instantiate the Email response.
   *
   * @return The {@link com.iquest.advancedframeworks.internetbanking.integration.jms.model.integrationclient.xml.Email.response.EmailResponse}
   */
  public EmailResponse createEmailResponse() {
    return new EmailResponse();
  }
}
