package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.WithdrawalTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.WithdrawalTransaction;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;

/**
 * The WithdrawalTransactionServiceImpl offers services which interacts with WithdrawalTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class WithdrawalTransactionServiceImpl implements WithdrawalTransactionService {

  /**
   * Logger instance used to log information from the UserServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawalTransactionServiceImpl.class);

  /**
   * The repository of the WithdrawalTransaction objects
   */
  @Autowired
  WithdrawalTransactionDao withdrawalTransactionDao;

  /**
   * The repository of the Account objects
   */
  @Autowired
  AccountDao accountDao;

  @Override
  @Transactional
  public void addTransaction(Account sender, double value) {
    sender.setAmount(sender.getAmount() - value);
    accountDao.update(sender);

    WithdrawalTransaction transaction = new WithdrawalTransaction();
    transaction.setSenderAccount(sender);
    transaction.setValue(value);

    LOGGER.info("new withdrawal transaction");
    withdrawalTransactionDao.create(transaction);
  }

}
