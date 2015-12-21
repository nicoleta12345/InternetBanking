package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iquest.advancedframeworks.internetbanking.services.AdminService;
import com.iquest.advancedframeworks.internetbanking.services.ClientService;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.ClientDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.RegistrationAccountInfDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.services.exceptions.UserRegisteredException;

/**
 * The AccountController class expose services for admin.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

  /**
   * Logger object used to log informations in the methods.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

  /**
   * The admin exposed services.
   */
  @Autowired
  private AdminService adminService;

  /**
   * The client exposed services.
   */
  @Autowired
  private ClientService clientService;

  /**
   * The transfer transaction services.
   */
  @Autowired
  private TransferTransactionService transferService;

  /**
   * Registers a new client.
   * 
   * @param clientDto the new client details
   * @throws UserRegisteredException if the user is already registered
   */
  @RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = "application/json")
  public void createNewClient(@RequestBody ClientDto clientDto) throws UserRegisteredException {
    try {
      clientService.insertUser(clientDto);
    } catch (UserRegisteredException e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }
  }

  /**
   * Creates a new account.
   * 
   * @param accountType the type of the account
   * @param regAccountInfDto the details about the new account
   * @throws AccountRegisteredException if the account already exists
   */
  @RequestMapping(value = "/accounts/{accountType}", method = RequestMethod.POST, consumes = "application/json")
  public void createNewClient(@PathVariable String accountType, @RequestBody RegistrationAccountInfDto regAccountInfDto)
      throws AccountRegisteredException {
    try {
      adminService.registerNewAccount(regAccountInfDto, accountType);
    } catch (AccountRegisteredException e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }
  }

  /**
   * Gets the pending transactions which need the approval of the admin.
   * 
   * @return a list with the pending transactions
   */
  @RequestMapping(value = "/pendingTransactions", method = RequestMethod.GET)
  public List<TransferTransactionDto> getPendingTransactions() {
    return transferService.getPendingTransactions();
  }

}
