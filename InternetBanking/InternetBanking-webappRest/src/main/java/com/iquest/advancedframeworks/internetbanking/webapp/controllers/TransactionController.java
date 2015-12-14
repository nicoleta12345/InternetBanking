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
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.ServicesException;

@RestController
@RequestMapping("/clients/{clientId}/transactions")
public class TransactionController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

  @Autowired
  DepositTransactionService depositTransactionService;

  @Autowired
  TransferTransactionService transferTransactionService;

  @Autowired
  WithdrawalTransactionService withdrawalTransactionService;

  @RequestMapping(method = RequestMethod.GET)
  public List<TransactionDto> getClientTransactions(@PathVariable Integer clientId) {
    return null;
  }

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

  @RequestMapping(value = "/transfer", method = RequestMethod.POST)
  public void doTransferTransactions(@PathVariable Integer clientId, @RequestBody TransferTransactionDto transferDto)
      throws ServicesException {
    try {
      transferTransactionService.addTransaction(transferDto, clientId);
    }
    catch (AccountAccessDenied | AccountNotFound e) {
      LOGGER.debug(e.getMessage(), e.getStackTrace());
      throw e;
    }
  }

  @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
  public void doWithdrawalTransactions(@PathVariable Integer clientId,
      @RequestBody WithdrawalTransactionDto withdrawalDto) {
    // 
  }

}
