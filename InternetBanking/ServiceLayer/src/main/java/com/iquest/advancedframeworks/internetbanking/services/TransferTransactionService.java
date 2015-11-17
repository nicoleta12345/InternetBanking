package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

public interface TransferTransactionService {

	void addTransaction(Account sender, Account receiver, double value);
}
