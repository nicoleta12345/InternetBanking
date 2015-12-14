package com.iquest.advancedframeworks.internetbanking.webapp.exceptions;

public class ServerErrorException extends RestException {
  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new exception with null as its detail message.
   */
  public ServerErrorException() {
  }

  /**
   * Constructs a new exception with the specified detail message.
   * 
   * @param message the detail message
   */
  public ServerErrorException(String message) {
    super(message);
  }

}
