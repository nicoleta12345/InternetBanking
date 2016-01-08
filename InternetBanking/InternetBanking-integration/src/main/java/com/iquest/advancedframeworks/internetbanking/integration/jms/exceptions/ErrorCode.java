package com.iquest.advancedframeworks.internetbanking.integration.jms.exceptions;

public enum ErrorCode {

  OK(100, "The message have been sent"), 
  FAIL_1(200, "There was a problem and the message could not be send"), 
  FAIL_2(300, "There was a problem and the message could not be send"), 
  FAIL_3(400, "There was a problem and the message could not be send");

  private final int code;
  private final String message;

  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
