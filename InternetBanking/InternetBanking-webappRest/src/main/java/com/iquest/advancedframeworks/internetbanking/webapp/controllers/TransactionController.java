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

import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.DepositTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.WithdrawalTransactionDto;
import com.iquest.advancedframeworks.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;

/**
 * The AccountController class expose services for user transactions.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@RestController
@RequestMapping("/clients/{clientId}/transactions")
public class TransactionController {

  /**
   * Logger used to log useful informations about what happens into the methods. 
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

  /**
   * The deposit transaction services.
   */
  @Autowired
  private DepositTransactionService depositTransactionService;

  /**
   * The transfer transaction services.
   */
  @Autowired
  private TransferTransactionService transferTransactionService;

  /**
   * The withdrawal transaction services.
   */
  @Autowired
  private WithdrawalTransactionService withdrawalTransactionService;

  /**
   * Gets the transactions a client have made.
   * 
   * @param clientId the id of the client
   * @return a list with the client transactions
   */
  @RequestMapping(method = RequestMethod.GET)
  public List<TransactionDto> getClientTransactions(@PathVariable Integer clientId) {
    return null;
  }

  /**
   * Makes a deposit transaction.
   * 
   * @param clientId the id of the client
   * @param depositDto the details about the transaction
   * @throws AccountNotFound if the receiver account does not exists
   */
  @RequestMapping(value = "/deposit", method = RequestMethod.POST)
  public void doDepositTransactions(@PathVariable Integer clientId, @RequestBody DepositTransactionDto depositDto)
      throws AccountNotFound {
    try {
      depositTransactionService.addTransaction(depositDto);
    }
    catch (AccountNotFound e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }
  }

  /**
   * Makes a transfer transaction.
   * 
   * @param clientId the id of the client
   * @param transferDto the details about the transaction
   * @throws AccountAccessDenied if the client tries to make a transfer from an account he doesn't own
   * @throws AccountNotFound if the sender account is not found
   */
  @RequestMapping(value = "/transfer", method = RequestMethod.POST)
  public void doTransferTransactions(@PathVariable Integer clientId, @RequestBody TransferTransactionDto transferDto)
      throws AccountAccessDenied, AccountNotFound {
    try {
      transferTransactionService.addTransaction(transferDto, clientId);
    }
    catch (AccountAccessDenied | AccountNotFound e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }
  }

  /**
   * Makes a withdrawal transaction.
   * 
   * @param clientId the id of the client
   * @param withdrawalDto the details about the transaction
   * @throws AccountAccessDenied if the client tries to make a transfer from an account he doesn't own
   * @throws AccountNotFound if the sender account is not found
   */
  @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
  public void doWithdrawalTransactions(@PathVariable Integer clientId,
      @RequestBody WithdrawalTransactionDto withdrawalDto) throws AccountAccessDenied, AccountNotFound {
    try {
      withdrawalTransactionService.addTransaction(withdrawalDto, clientId);
    }
    catch (AccountAccessDenied | AccountNotFound e) {
      LOGGER.debug(e.getClass().getSimpleName(), e.getStackTrace());
      throw e;
    }
  }

}
