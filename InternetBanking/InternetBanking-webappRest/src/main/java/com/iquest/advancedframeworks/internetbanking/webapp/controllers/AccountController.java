package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.ClientService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.ClientDto;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.services.exceptions.UserNotFound;

@RestController
@RequestMapping("/clients/{clientId}/accounts")
public class AccountController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  AccountService accountService;

  @Autowired
  ClientService clientService;

  /**
   * Gets the accounts of a client.
   * 
   * @param clientId the id of the client
   * @return a list with the client accounts
   * @throws UserNotFound if the user with the given id does not exist
   */
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<AccountDetailsDto>> getClientAccounts(@PathVariable Integer clientId) throws UserNotFound {
    ClientDto client = null;

    try {
      client = clientService.getUserbyId(clientId);
    } catch (UserNotFound e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }
    return new ResponseEntity<List<AccountDetailsDto>>(client.getAccounts(), HttpStatus.OK);
  }

  /**
   * Gets an account identified by its number.
   * 
   * @param accountNumber the identifier of the account
   * @return the account identified by the given number
   * @throws AccountNotFound if the account could not be found
   */
  @RequestMapping("/{accountNumber}")
  public ResponseEntity<AccountDetailsDto> getAccountByNo(@PathVariable String accountNumber) throws AccountNotFound {
    AccountDetailsDto accountDetailsDto = null;
    
    try {
      accountDetailsDto = accountService.getAccountByNo(accountNumber);
    } catch (AccountNotFound e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }

    return new ResponseEntity<AccountDetailsDto>(accountDetailsDto, HttpStatus.OK);
  }

}
