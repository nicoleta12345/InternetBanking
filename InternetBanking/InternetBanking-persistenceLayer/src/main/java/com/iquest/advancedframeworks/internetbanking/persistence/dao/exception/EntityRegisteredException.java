package com.iquest.advancedframeworks.internetbanking.persistence.dao.exception;

/**
 * The EntityRegisteredException exception is a persistence specific exception and should be thrown when the user tries
 * to insert a user and the user already exists.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class EntityRegisteredException extends DaoException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public EntityRegisteredException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public EntityRegisteredException(String message) {
    super(message);
  }

}
