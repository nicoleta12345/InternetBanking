package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iquest.advancedframeworks.internetbanking.services.AdminService;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.RegistrationAccountInfDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.UserDto;
import com.iquest.advancedframeworks.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.services.exceptions.UserRegisteredException;

/**
 * The AdminController class represents a controller which interacts with the admin specific views and the service
 * implementations. It is mapped to a certain url.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

  /**
   * The service which offers services for the admin.
   */
  @Autowired
  AdminService adminService;

  @Autowired
  TransferTransactionService transferService;

  /**
   * Displays the form to register a new client.
   * 
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/clientRegistrationForm", method = RequestMethod.GET)
  public String clientRegistrationForm() {
    return "clientRegistrationForm";
  }

  /**
   * Registers a new client.
   * 
   * @param userDto contains details about the new client
   * @param model the Model object on which are set the attributes for the view
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/registerClient", method = RequestMethod.POST)
  public String registerNewClient(@ModelAttribute @Valid UserDto userDto, BindingResult binding, Model model) {
    if (binding.hasErrors()) {
      return "clientRegistrationForm";
    }

    try {
      adminService.registerNewClient(userDto);
    }
    catch (UserRegisteredException e) {
      model.addAttribute("errorMessage", "The client is already registered!");
      return "clientRegistrationForm";
    }

    model.addAttribute("message", "The client was registered!");
    return "operationSuccess";
  }

  /**
   * Displays the from to register a new account.
   * 
   * @param model the Model object on which are set the attributes for the view
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
  public String createAccountForm(Model model) {
    return "accountRegistrationForm";
  }

  /**
   * Registers a new account.
   * 
   * @param accountDetails the details about the new account
   * @param clientCnp the cnp of the owner of the account
   * @param model the Model object on which are set the attributes for the view
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
  public String registerNewAccount(@ModelAttribute @Valid RegistrationAccountInfDto registrationAccountInfDto,
      BindingResult bindingResult, @RequestParam String accountType, Model model) {
    if (bindingResult.hasErrors()) {
      return "accountRegistrationForm";
    }

    try {
      adminService.registerNewAccount(registrationAccountInfDto, accountType);
    }
    catch (AccountRegisteredException e) {
      model.addAttribute("errorMessage", "The account is already registered!");
      return "accountRegistrationForm";
    }

    model.addAttribute("message", "The account was registered");
    return "operationSuccess";
  }

  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/pendingTransactions", method = RequestMethod.GET)
  public String getPendingTransactions(Model model) {
    List<TransferTransactionDto> pendingTransactions = transferService.getPendingTransactions();

    model.addAttribute("pendingTransactions", pendingTransactions);
    System.out.println(pendingTransactions);
    return "pendingTransactions";
  }

  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/acceptTransaction/{id}", method = RequestMethod.GET)
  public String acceptTransaction(@PathVariable("id") int id) {
    adminService.acceptTransaction(id);
    return "redirect:/admin/pendingTransactions";
  }

  @Secured("ROLE_ADMIN")
  @RequestMapping(value = "/declineTransaction/{id}", method = RequestMethod.GET)
  public String declineTransaction(@PathVariable("id") int id) {
    adminService.declineTransaction(id);
    return "redirect:/admin/pendingTransactions";
  }

}
