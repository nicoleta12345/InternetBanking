package com.iquest.advancedframeworks.internetbanking.integration.jms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iquest.advancedframeworks.internetbanking.integration.jms.model.response.EmailResponse;

/**
 * 
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class JmsEmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(JmsEmailService.class);

  public void onMessage(EmailResponse msg) {
    LOGGER.info("Received response with content: " + msg);
  }

}
