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
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.RegistrationAccountInfDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.services.exceptions.AccountRegisteredException;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

  @Autowired
  private AdminService adminService;

  @Autowired
  private TransferTransactionService transferService;

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
    }
    catch (AccountRegisteredException e) {
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
