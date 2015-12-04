package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.DepositTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

/**
 * The DepositTransactionService interface specifies services for DepositTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface DepositTransactionService {

  /**
   * Adds a deposit transaction.
   * 
   * @param receiver the receiver account
   * @param value the value of the transaction
   * @throws AccountNotFound thrown if the receiver account doesn't exist
   */
  void addTransaction(DepositTransactionDto deposiTransactionDto) throws AccountNotFound;

}
