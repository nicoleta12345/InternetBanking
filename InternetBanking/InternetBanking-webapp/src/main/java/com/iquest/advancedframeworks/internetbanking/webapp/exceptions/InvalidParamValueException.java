package com.iquest.advancedframeworks.internetbanking.webapp.exceptions;

/**
 * The InvalidParamValueException class represents exceptions which are thrown when a parameter in invalid.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class InvalidParamValueException extends RestException {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new exception with null as its detail message.
   */
  public InvalidParamValueException() {
  }

  /**
   * Constructs a new exception with the specified detail message.
   * 
   * @param message the detail message
   */
  public InvalidParamValueException(String message) {
    super(message);
  }

}
