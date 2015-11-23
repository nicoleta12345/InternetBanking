package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.UserService;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserNotFound;

/**
 * The AccountController class represents a controller which interacts with the account specific views and the service
 * implementations. It is mapped to a certain url.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {

  /**
   * The accounts service.
   */
  @Autowired
  AccountService accountService;

  /**
   * The users service.
   */
  @Autowired
  UserService userService;

  /**
   * Displays the jsp view corresponding to the search account.
   * 
   * @return the name of the view(jsp)
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/accountForm", method = RequestMethod.GET)
  public String showFormGetIdUser() {
    return "account";
  }

  /**
   * Gets a searched account from the database and displays informations about it in a jsp view. The account is
   * identified by its number.
   * 
   * @param session
   * @param accountNumber the identifier of the account
   * @param model the Model object used to set attributes in the returned view
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/getAccount", method = RequestMethod.POST)
  public String getUser(HttpSession session, @RequestParam String accountNumber, Model model) {
    String loggedUserUsername = (String) session.getAttribute("user");
    Account account;
    User user = null;
    
    try {
      account = accountService.getAccountByNo(accountNumber);
      user = userService.getUserByAccount(account);
    }
    catch (AccountNotFound | UserNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");

      return "error";
    }    

    if (user.getUsername() != loggedUserUsername) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");

      return "error";
    }
    
    model.addAttribute("accountNo", accountNumber);
    model.addAttribute("amount", account.getAmount());

    return "accountDetails";
  }

}
