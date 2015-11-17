package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.dao.DepositTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.DepositTransaction;
import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;

@Service
public class DepositTransactionServiceImpl implements DepositTransactionService {

	@Autowired
	DepositTransactionDao depositTransactionDao;

	@Autowired
	AccountDao accountDao;

	@Override
	@Transactional
	public void addTransaction(Account receiver, double value) {
		receiver.setAmount(receiver.getAmount() + value);
		accountDao.updateAccount(receiver);

		DepositTransaction transaction = new DepositTransaction();
		transaction.setReceiverAccount(receiver);
		transaction.setValue(value);

		depositTransactionDao.createTransaction(transaction);
	}

}
