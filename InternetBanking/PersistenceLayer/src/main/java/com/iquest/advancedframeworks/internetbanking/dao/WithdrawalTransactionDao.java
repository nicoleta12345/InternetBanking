package com.iquest.advancedframeworks.internetbanking.dao;

import com.iquest.advancedframeworks.internetbanking.model.WithdrawalTransaction;

public interface WithdrawalTransactionDao {
  void createTransaction(WithdrawalTransaction withdrawalTransaction);

  WithdrawalTransaction readTransaction(Integer id);

  WithdrawalTransaction updateTransaction(
      WithdrawalTransaction withdrawalTransaction);

  void deleteTransaction(WithdrawalTransaction withdrawalTransaction);
}
