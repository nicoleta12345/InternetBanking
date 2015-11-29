package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionAccounts;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

/**
 * The TransferTransactionService interface specifies services for TransferTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface TransferTransactionService {

  /**
   * Gets the informations which will be displayed into the transfer page.
   * 
   * @param username the current user username
   * @return the information needed to display into the transfer page
   */
  TransactionAccounts getFormData(String username);

  /**
   * Adds a transfer transaction.
   * 
   * @param transferTransaction the details about the transfer
   * @param currentUserUsername the username of the current logged in user
   * @throws AccountAccessDenied if the current user is not the owner of the sender account involved in the transfer
   * @throws AccountNotFound if the sender account is not found
   */
  void addTransaction(TransferTransactionDto transferTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound;

}
