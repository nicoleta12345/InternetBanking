package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransferTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.TransferTransaction;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;

/**
 * The TransferTransactionServiceImpl offers services which interacts with TransferTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class TransferTransactionServiceImpl implements TransferTransactionService {

  /**
   * Logger instance used to log information from the TransferTransactionServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(TransferTransactionServiceImpl.class);

  /**
   * The repository for the TransferTransaction entities.
   */
  @Autowired
  TransferTransactionDao transferTransactionDao;

  /**
   * The repository for the Account entities.
   */
  @Autowired
  AccountDao accountDao;

  @Override
  @Transactional
  public void addTransaction(Account sender, Account receiver, double value) {
    sender.setAmount(sender.getAmount() - value);
    accountDao.update(sender);

    receiver.setAmount(receiver.getAmount() + value);
    accountDao.update(receiver);

    TransferTransaction transaction = new TransferTransaction();
    transaction.setSenderAccount(sender);
    transaction.setReceiverAccount(receiver);
    transaction.setValue(value);

    LOGGER.info("New transfer transaction");
    transferTransactionDao.create(transaction);
  }

}
