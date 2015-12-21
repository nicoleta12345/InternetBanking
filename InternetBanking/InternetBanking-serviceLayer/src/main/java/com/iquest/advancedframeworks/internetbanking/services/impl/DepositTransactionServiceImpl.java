package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Deposit;
import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.DepositTransactionDto;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;

/**
 * The DepositTransactionServiceImpl offers services which interacts with DepositTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class DepositTransactionServiceImpl implements DepositTransactionService {

  /**
   * Logger instance used to log information from the DepositTransactionServiceImpl class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(DepositTransactionServiceImpl.class);

  /**
   * The repository for the Transaction objects.
   */
  @Autowired
  TransactionDao transactionDao;

  /**
   * The repository for the Account objects.
   */
  @Autowired
  AccountDao accountDao;

  @Override
  @Transactional
  public void addTransaction(DepositTransactionDto depositTransactionDto)
      throws AccountNotFound {
    Account receiver = updateReceiverAccount(depositTransactionDto);

    Deposit transaction = new Deposit();
    transaction.setReceiverAccount(receiver);
    transaction.setValue(depositTransactionDto.getValue());
    try {
      transactionDao.create(transaction);
    } catch (EntityRegisteredException e) {
      // stay silent
    }

    LOGGER.info("New deposit transaction");
  }

  /**
   * Updates the details of the receiver account.
   * 
   * @param depositTransactionDto contains details about the deposit transaction
   * @return the updated receiver account
   * @throws AccountNotFound if the account could not be found
   */
  private Account updateReceiverAccount(DepositTransactionDto depositTransactionDto)
      throws AccountNotFound {
    Account receiver = accountDao.getAccountByNo(depositTransactionDto.getReceiverAccountNumber());
    if (receiver == null) {
      throw new AccountNotFound("The account doesn't exist");
    }

    receiver.setAmount(receiver.getAmount() + depositTransactionDto.getValue());
    try {
      receiver = accountDao.update(receiver);
    } catch (EntityDeletedException e) {
      throw new AccountNotFound("The account which should be updated doesn't exist!");
    }

    return receiver;
  }

}
