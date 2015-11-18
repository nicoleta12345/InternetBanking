package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The TransferTransactionService interface specifies services for
 * TransferTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface TransferTransactionService {

  /**
   * Adds a transfer transaction.
   * 
   * @param sender
   *          the sender account
   * @param receiver
   *          the receiver account
   * @param value
   *          the value of the transaction
   */
  void addTransaction(Account sender, Account receiver, double value);
}
