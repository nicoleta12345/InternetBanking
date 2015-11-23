package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransferTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.TransferTransaction;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;

@Service
public class TransferTransactionServiceImpl implements TransferTransactionService {

	@Autowired
	TransferTransactionDao transferTransactionDao;
	
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

		transferTransactionDao.create(transaction);		
	}

}
