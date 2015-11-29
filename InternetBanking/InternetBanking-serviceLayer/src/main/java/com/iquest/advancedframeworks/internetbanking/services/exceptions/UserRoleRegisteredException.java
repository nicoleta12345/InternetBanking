package com.iquest.advancedframeworks.internetbanking.services.exceptions;

/**
 * The UserRoleRegisteredException exception should be thrown if somebody tries to register a UserRole which is already
 * registered.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class UserRoleRegisteredException extends ServicesException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public UserRoleRegisteredException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public UserRoleRegisteredException(String message) {
    super(message);
  }

}
