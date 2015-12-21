package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountFormDataDto;
import com.iquest.advancedframeworks.services.exceptions.AccountAccessDenied;

/**
 * The AccountController class represents a controller which interacts with the account specific views and the service
 * implementations. It is mapped to a certain url.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
@RequestMapping("/user/account")
public class AccountController {

  /**
   * Logger object used to log informations in the methods.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
  
  /**
   * The accounts service.
   */
  @Autowired
  AccountService accountService;

  /**
   * Displays the jsp view corresponding to the search account.
   * 
   * @return the name of the view(jsp)
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String showFormGetIdUser(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    AccountFormDataDto accountFormDataDto = accountService.getFormData(username);

    model.addAttribute(accountFormDataDto);
    return "account";
  }

  /**
   * Gets a searched account from the database and displays informations about it in a jsp view. The account is
   * identified by its number.
   * 
   * @param session the HttpSession object
   * @param accountNumber the identifier of the account
   * @param model the Model object used to set attributes in the returned view
   * @return the name of the view which will be rendered
   * @throws AccountAccessDenied if the user is not the owner of the account
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/getAccount", method = RequestMethod.POST)
  public String getAccount(HttpSession session, @RequestParam String accountNumber, Model model) throws AccountAccessDenied {
    String currentUserUsername = (String) session.getAttribute("username");
    AccountDetailsDto accountDetailsDto;

    try {
      accountDetailsDto = accountService.getAccountDetails(accountNumber, currentUserUsername);
    } catch (AccountAccessDenied e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }

    model.addAttribute(accountDetailsDto);
    return "accountDetails";
  }

}
