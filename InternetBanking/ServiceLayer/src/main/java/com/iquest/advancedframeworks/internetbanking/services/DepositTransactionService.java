package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The DepositTransactionService interface specifies services for
 * DepositTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface DepositTransactionService {

  /**
   * Adds a deposit transaction.
   * 
   * @param receiver
   *          the receiver account
   * @param value
   *          the value of the transaction
   */
  void addTransaction(Account receiver, double value);

}
