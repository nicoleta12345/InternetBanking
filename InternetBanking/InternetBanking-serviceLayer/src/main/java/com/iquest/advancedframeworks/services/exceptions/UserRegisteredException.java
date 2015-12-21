package com.iquest.advancedframeworks.services.exceptions;

/**
 * The UserRegisteredException exception should be thrown if someone tries to register a user already registered.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class UserRegisteredException extends ServicesException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public UserRegisteredException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public UserRegisteredException(String message) {
    super(message);
  }

}
