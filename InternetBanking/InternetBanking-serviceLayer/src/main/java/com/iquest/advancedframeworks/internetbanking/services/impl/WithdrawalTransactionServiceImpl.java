package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.WithdrawalTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.WithdrawalTransaction;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;

@Service
public class WithdrawalTransactionServiceImpl implements
    WithdrawalTransactionService {

  @Autowired
  WithdrawalTransactionDao withdrawalTransactionDao;

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

    withdrawalTransactionDao.create(transaction);
  }

}
