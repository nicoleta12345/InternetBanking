package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.DepositTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.DepositTransaction;
import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;

/**
 * The DepositTransactionServiceImpl offers services which interacts with DepositTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class DepositTransactionServiceImpl implements DepositTransactionService {

  /**
   * The repository for the DepositTransaction objects
   */
	@Autowired
	DepositTransactionDao depositTransactionDao;

	/**
	 * The repository for the Account objects
	 */
	@Autowired
	AccountDao accountDao;

	@Override
	@Transactional
	public void addTransaction(Account receiver, double value) {
		receiver.setAmount(receiver.getAmount() + value);
		accountDao.update(receiver);

		DepositTransaction transaction = new DepositTransaction();
		transaction.setReceiverAccount(receiver);
		transaction.setValue(value);

		depositTransactionDao.create(transaction);
	}

}
