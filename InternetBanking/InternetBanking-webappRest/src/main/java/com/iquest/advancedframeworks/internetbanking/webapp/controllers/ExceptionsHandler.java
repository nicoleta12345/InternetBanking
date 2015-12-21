package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iquest.advancedframeworks.internetbanking.webapp.controllers.exceptions.ErrorDetails;
import com.iquest.advancedframeworks.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.services.exceptions.UserNotFound;
import com.iquest.advancedframeworks.services.exceptions.UserRegisteredException;

/**
 * The ExceptionsHandler class handles all exceptions thrown from the controller methods.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@ControllerAdvice
public class ExceptionsHandler {

  /**
   * Handles the UserNotFound exception.
   * 
   * @param e the exception object
   * @return a ResponseEntity object which contains the error message and the status code.
   */
  @ExceptionHandler(UserNotFound.class)
  public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFound e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("404");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles the AccountNotFound exception.
   * 
   * @param e the exception object
   * @return a ResponseEntity object which contains the error message and the status code.
   */
  @ExceptionHandler(AccountNotFound.class)
  public ResponseEntity<ErrorDetails> handleAccountNotFound(AccountNotFound e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("404");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles the AccountRegisteredException exception.
   * 
   * @param e the exception object
   * @return a ResponseEntity object which contains the error message and the status code.
   */
  @ExceptionHandler(AccountRegisteredException.class)
  public ResponseEntity<ErrorDetails> handleAccountNotFound(AccountRegisteredException e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("409");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
  }

  /**
   * Handles the UserRegisteredException exception.
   * 
   * @param e the exception object
   * @return a ResponseEntity object which contains the error message and the status code.
   */
  @ExceptionHandler(UserRegisteredException.class)
  public ResponseEntity<ErrorDetails> handleUserRegisteredException(UserRegisteredException e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("409");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
  }

  /**
   * Handles the AccountAccessDenied exception.
   * 
   * @param e the exception object
   * @return a ResponseEntity object which contains the error message and the status code.
   */
  @ExceptionHandler(AccountAccessDenied.class)
  public ResponseEntity<ErrorDetails> handleAccountAccessDenied(AccountAccessDenied e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("403");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.FORBIDDEN);
  }

}
