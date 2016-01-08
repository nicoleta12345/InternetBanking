package com.iquest.advancedframeworks.internetbanking.webapp.exceptions;

/**
 * The MissingParamException class represents the which is thrown when the parameter is missing.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class MissingParamException extends RestException {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new exception with null as its detail message.
   */
  public MissingParamException() {
  }

  /**
   * Constructs a new exception with the specified detail message.
   * 
   * @param message the detail message
   */
  public MissingParamException(String message) {
    super(message);
  }
  
}
