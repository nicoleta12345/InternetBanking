package com.iquest.advancedframeworks.internetbanking.persistence.dao.exception;

/**
 * The PersistenceFailure exception should be thrown if there is a persistence failure.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class PersistenceFailure extends DaoException {

  /**
   * Default serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public PersistenceFailure() {
  };

  /**
   * Parameterized constructor.
   * 
   * @param message a specific message for the exception
   */
  public PersistenceFailure(String message) {
    super(message);
  }

}
