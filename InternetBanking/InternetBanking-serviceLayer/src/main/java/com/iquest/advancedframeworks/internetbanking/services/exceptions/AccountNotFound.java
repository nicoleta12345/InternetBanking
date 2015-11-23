package com.iquest.advancedframeworks.internetbanking.services.exceptions;

/**
 * The AccountNotFound should be thrown when the user searches for an Account object which doesn't exist in the database.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class AccountNotFound extends ServicesException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public AccountNotFound() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public AccountNotFound(String message) {
    super(message);
  }

}
