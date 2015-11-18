package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The WithdrawalTransactionService interface specifies services for
 * WithdrawalTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface WithdrawalTransactionService {

  /**
   * Adds a withdrawal transaction.
   * 
   * @param sender
   *          the sender account
   * @param value
   *          the receiver account
   */
  void addTransaction(Account sender, double value);

}
