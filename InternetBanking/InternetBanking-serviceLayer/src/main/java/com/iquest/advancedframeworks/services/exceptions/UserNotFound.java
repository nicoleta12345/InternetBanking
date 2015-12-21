package com.iquest.advancedframeworks.services.exceptions;

/**
 * The UserNotFound exception is thrown when a user can not be found, when there is expected a result.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class UserNotFound extends ServicesException {

  /**
   * The serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public UserNotFound() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public UserNotFound(String message) {
    super(message);
  }

}
