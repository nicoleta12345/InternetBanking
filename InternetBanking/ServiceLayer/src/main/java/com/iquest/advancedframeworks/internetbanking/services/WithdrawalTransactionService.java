package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

public interface WithdrawalTransactionService {
  void addTransaction(Account sender, double value);
}
