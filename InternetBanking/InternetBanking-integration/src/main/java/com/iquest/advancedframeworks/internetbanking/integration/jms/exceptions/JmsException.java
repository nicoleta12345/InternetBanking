package com.iquest.advancedframeworks.internetbanking.integration.jms.exceptions;

public class JmsException extends IntegrationException {

  private ErrorCode errorCode;
  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public JmsException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public JmsException(String message) {
    super(message);
  }
}
