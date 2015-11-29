package com.iquest.advancedframeworks.internetbanking.services.exceptions;

/**
 * The AccountAccessDenied class is a specific ServiceException which should be thrown if a user intends to make an
 * operation with an account he doesn't own.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class AccountAccessDenied extends ServicesException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public AccountAccessDenied() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public AccountAccessDenied(String message) {
    super(message);
  }

}