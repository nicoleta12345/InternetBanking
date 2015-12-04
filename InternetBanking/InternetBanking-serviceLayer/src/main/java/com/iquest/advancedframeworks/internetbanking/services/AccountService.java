package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountFormDataDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountRegisteredException;

/**
 * The AccountService interface represents a service which operates with Account objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface AccountService {

  /**
   * Calls certain methods to insert a new entry into the database for a certain Account object.
   * 
   * @param account the account which will be inserted into the database
   * @throws AccountRegisteredException if the account already exists
   */
  void createAccount(AccountDetailsDto account) throws AccountRegisteredException;

  /**
   * Gets an account by its number.
   * 
   * @param accountNo the account identifier(the account number)
   * @return the Account object identified by its number
   * @throws AccountNotFound if the account could not be found
   */
  AccountDetailsDto getAccountByNo(String accountNo) throws AccountNotFound;

  /**
   * Updates an account.
   * 
   * @param account the updated details of the account
   * @return the updated Account object
   * @throws AccountNotFound thrown if the account doesn't exists
   */
  AccountDetailsDto updateAccount(AccountDetailsDto account) throws AccountNotFound;

  /**
   * Gets an account identified by its number. This method also checks if the current logged in useris the owner of the
   * account. If there was a mistake and this is not the case where they are the same there will be thrown an exception.
   * 
   * @param accountNumber the account number
   * @param currentUserUsername the username of the current logged in user
   * @return an object which contains details about the account
   * @throws AccountAccessDenied if the current logged in user is not the owner of the account
   */
  AccountDetailsDto getAccountDetails(String accountNumber, String currentUserUsername) throws AccountAccessDenied;

  /**
   * Gets an object used to fill fields into a specific view.
   * 
   * @param currentUserUsername the name of the current logged in user
   * @return an object which contains informations about the accounts of an user
   */
  AccountFormDataDto getFormData(String currentUserUsername);

}
