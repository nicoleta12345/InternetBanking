package com.iquest.advancedframeworks.internetbanking.webapp.controllers.exceptions;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.ApplicationException;

/**
 * The RestBaseException class represents the base class for exceptions thrown in REST implementation.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class RestException extends ApplicationException {

  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new exception with null as its detail message.
   */
  public RestException() {
  }

  /**
   * Constructs a new exception with the specified detail message.
   * 
   * @param message the detail message
   */
  public RestException(String message) {
    super(message);
  }

}
