package com.iquest.advancedframeworks.internetbanking.services.exceptions;

/**
 * The AccountRegisteredException exception should be thrown when someone tries to register an account already
 * registered.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class AccountRegisteredException extends ServicesException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public AccountRegisteredException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public AccountRegisteredException(String message) {
    super(message);
  }

}
