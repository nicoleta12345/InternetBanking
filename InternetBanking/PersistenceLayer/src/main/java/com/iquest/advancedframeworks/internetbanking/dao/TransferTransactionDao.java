package com.iquest.advancedframeworks.internetbanking.dao;

import com.iquest.advancedframeworks.internetbanking.model.Transaction;
import com.iquest.advancedframeworks.internetbanking.model.TransferTransaction;

public interface TransferTransactionDao {

	void createTransaction(TransferTransaction transferTransaction);

	TransferTransaction readTransaction(Integer id);

	TransferTransaction updateTransaction(TransferTransaction transferTransaction);

	void deleteTransaction(TransferTransaction transferTransaction);
}
