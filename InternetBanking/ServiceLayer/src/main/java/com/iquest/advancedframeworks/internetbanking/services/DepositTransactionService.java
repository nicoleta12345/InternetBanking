package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

public interface DepositTransactionService {

	void addTransaction(Account receiver, double value);
}
