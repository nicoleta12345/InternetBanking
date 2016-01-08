package com.iquest.advancedframeworks.internetbanking.integration.jms.exceptions;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.ApplicationException;

public class IntegrationException extends ApplicationException {
  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public IntegrationException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public IntegrationException(String message) {
    super(message);
  }

}
