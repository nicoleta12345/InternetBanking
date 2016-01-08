package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.services.exceptions.UserNotFound;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(UserNotFound.class)
  public String handleUserNotFound(HttpServletRequest req, UserNotFound exception, Model model) {
    model.addAttribute("errorMessage", exception.getMessage());
    return "error";
  }
  

  @ExceptionHandler(AccountNotFound.class)
  public String handleAccountNotFound(HttpServletRequest req, AccountNotFound exception, Model model) {
    model.addAttribute("errorMessage", exception.getMessage());
    return "error";
  }
  
}
