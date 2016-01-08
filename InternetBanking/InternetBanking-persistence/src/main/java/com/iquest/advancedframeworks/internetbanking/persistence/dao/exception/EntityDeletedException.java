package com.iquest.advancedframeworks.internetbanking.persistence.dao.exception;

/**
 * The EntityDeletedException exception is a persistence specific exception and it should be thrown when the user try to
 * tries to modify an entry from the database which doesn't exist anymore.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class EntityDeletedException extends DaoException {

  /**
   * The default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public EntityDeletedException() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public EntityDeletedException(String message) {
    super(message);
  }

}
