package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iquest.advancedframeworks.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.services.exceptions.UserNotFound;

/**
 * The ExceptionsHandlerController class handles exceptions thrown from the controller methods.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@ControllerAdvice
public class ExceptionsHandlerController {

  /**
   * Handles the UserNotFound exception.
   * 
   * @param exception the exception which is thrown
   * @param model the Model object used to set attributes in the returned view
   * @return the name of the view which will be rendered
   */
  @ExceptionHandler(UserNotFound.class)
  public String handleUserNotFound(UserNotFound exception, Model model) {
    model.addAttribute("errorMessage", exception.getMessage());
    return "error";
  }
  
  /**
   * Handles the AccountNotFound exception.
   * 
   * @param exception the exception which is thrown
   * @param model the Model object used to set attributes in the returned view
   * @return the name of the view which will be rendered
   */
  @ExceptionHandler(AccountNotFound.class)
  public String handleAccountNotFound(AccountNotFound exception, Model model) {
    model.addAttribute("errorMessage", exception.getMessage());
    return "error";
  }
  
  /**
   * Handles the AccountRegisteredException exception.
   * 
   * @param exception the exception which is thrown
   * @param model the Model object used to set attributes in the returned view
   * @return the name of the view which will be rendered
   */
  @ExceptionHandler(AccountRegisteredException.class)
  public String handleAccountRegisteredException(AccountRegisteredException exception, Model model) {
    model.addAttribute("errorMessage", exception.getMessage());
    return "error";
  }
  
  /**
   * Handles the AccountAccessDenied exception.
   * 
   * @param exception the exception which is thrown
   * @param model the Model object used to set attributes in the returned view
   * @param session the current session
   * @return the name of the view which will be rendered
   */
  @ExceptionHandler(AccountAccessDenied.class)
  public String handleAccountAccessDenied(HttpSession session, AccountAccessDenied exception, Model model) {
    model.addAttribute("user", session.getAttribute("username"));
    return "accessDenied";
  }
  
}
