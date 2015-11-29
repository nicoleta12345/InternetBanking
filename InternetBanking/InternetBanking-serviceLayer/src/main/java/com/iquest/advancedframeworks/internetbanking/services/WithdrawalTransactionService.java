package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionAccounts;
import com.iquest.advancedframeworks.internetbanking.services.dto.WithdrawalTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

/**
 * The WithdrawalTransactionService interface specifies services for WithdrawalTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface WithdrawalTransactionService {

  /**
   *  Gets the details which will be displayed into the page of a withdrawal.
   *  
   * @param currentUserUsername the username of the current logged in user
   * @return the information needed to display into the withdrawal page
   */
  TransactionAccounts getFormData(String currentUserUsername);

  /**
   * Adds a withdrawal transaction.
   * 
   * @param withdrawalTransaction the details about the withdrawal transaction
   * @param currentUserUsername the username of the current user
   * @throws AccountAccessDeniedif the current user is not the owner of the sender account involved in the transfer
   * @throws AccountNotFound if the sender account is not found
   */
  void addTransaction(WithdrawalTransactionDto withdrawalTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound;

}
