package com.iquest.advancedframeworks.services.exceptions;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.ApplicationException;

/**
 * The ServicesException class represents the base class for the exceptions thrown from the service layer.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class ServicesException extends ApplicationException {

  /**
   * Default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public ServicesException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public ServicesException(String message) {
    super(message);
  }

}
