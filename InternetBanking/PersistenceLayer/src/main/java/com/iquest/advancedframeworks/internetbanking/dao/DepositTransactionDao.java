package com.iquest.advancedframeworks.internetbanking.dao;

import com.iquest.advancedframeworks.internetbanking.model.DepositTransaction;

public interface DepositTransactionDao {

	void createTransaction(DepositTransaction depositTransaction);

	DepositTransaction readTransaction(Integer id);

	DepositTransaction updateTransaction(DepositTransaction transaction);

	void deleteTransaction(DepositTransaction transaction);
}
