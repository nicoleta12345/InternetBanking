package com.iquest.advancedframeworks.internetbanking.persistence.dao.exception;

/**
 * The ApplicationException class represents the base class for the exceptions from application.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class ApplicationException extends Exception {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  public ApplicationException() {
  };

  public ApplicationException(String message) {
    super(message);
  }

}
