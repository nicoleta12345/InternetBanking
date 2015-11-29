package com.iquest.advancedframeworks.internetbanking.services.impl;

import javax.security.auth.login.AccountNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.DepositTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.DepositTransaction;
import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.DepositTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

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
   * The repository for the DepositTransaction objects.
   */
  @Autowired
  DepositTransactionDao depositTransactionDao;

  /**
   * The repository for the Account objects.
   */
  @Autowired
  AccountDao accountDao;

  @Override
  @Transactional
  public void addTransaction(DepositTransactionDto deposiTransactionDto) throws AccountNotFoundException, AccountNotFound {
    Account receiver = accountDao.getAccountByNo(deposiTransactionDto.getReceiverAccountNumber());

    if (receiver == null) {
      throw new AccountNotFoundException("The account doesn't exist");
    }

    receiver.setAmount(receiver.getAmount() + deposiTransactionDto.getValue());
    try {
      accountDao.update(receiver);
    }
    catch (EntityDeletedException e) {
      throw new AccountNotFound("The account which should be updated doesn't exist!");
    }

    DepositTransaction transaction = new DepositTransaction();
    transaction.setReceiverAccount(receiver);
    transaction.setValue(deposiTransactionDto.getValue());
    try {
      depositTransactionDao.create(transaction);
    }
    catch (EntityRegisteredException e) {
      //stay silent
    }

    LOGGER.info("New deposit transaction");
  }

}
