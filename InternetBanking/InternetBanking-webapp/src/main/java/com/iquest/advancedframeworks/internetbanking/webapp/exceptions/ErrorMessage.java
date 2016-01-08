package com.iquest.advancedframeworks.internetbanking.webapp.exceptions;

/**
 * The ErrorMessage keeps a message of an exception.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class ErrorMessage {

  private String error;
  
  public ErrorMessage(String error) {
    this.error = error;    
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
  
}
