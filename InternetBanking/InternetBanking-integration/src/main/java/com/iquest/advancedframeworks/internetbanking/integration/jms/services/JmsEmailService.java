package com.iquest.advancedframeworks.internetbanking.integration.jms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iquest.advancedframeworks.internetbanking.integration.jms.model.response.EmailResponse;

/**
 * The JmsEmailService class is a listener on a jms queue.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class JmsEmailService {

  /**
   * The logger for the JmsEmailService class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(JmsEmailService.class);

  /**
   * Listen for response on a jms queue.
   * 
   * @param msg the message received from the jms queue
   */
  public void onMessage(EmailResponse msg) {
    LOGGER.info("Received response with content: " + msg);
    if (msg.getErrorCode() == 500) {
      LOGGER.error(msg.getErrorMessage());
    } else {
      LOGGER.debug("The email was send successfuly");
    }
  }

}
