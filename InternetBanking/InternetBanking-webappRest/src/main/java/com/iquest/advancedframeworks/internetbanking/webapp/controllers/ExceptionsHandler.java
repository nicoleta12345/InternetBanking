package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iquest.advancedframeworks.internetbanking.webapp.controllers.exceptions.ErrorDetails;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.services.exceptions.UserNotFound;

@ControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(UserNotFound.class)
  public ResponseEntity<ErrorDetails> handleUserNotFound(UserNotFound e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("404");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AccountNotFound.class)
  public ResponseEntity<ErrorDetails> handleAccountNotFound(AccountNotFound e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("404");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AccountRegisteredException.class)
  public ResponseEntity<ErrorDetails> handleAccountNotFound(AccountRegisteredException e) {
    ErrorDetails error = new ErrorDetails();
    error.setMessage(e.getMessage());
    error.setCode("409");

    return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
  }
}
