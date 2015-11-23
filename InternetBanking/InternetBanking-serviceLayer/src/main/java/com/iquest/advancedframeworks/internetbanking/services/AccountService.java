package com.iquest.advancedframeworks.internetbanking.services;

import java.util.List;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

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
   */
  void createAccount(Account account);

  /**
   * Gets an account by its number.
   * 
   * @param accountNo the account identifier(the account number)
   * @return the Account object identified by its number
   * @throws AccountNotFound if the account could not be found
   */
  Account getAccountByNo(String accountNo) throws AccountNotFound;

  /**
   * Updates an account.
   * 
   * @param account the updated details of the account
   * @return the updated Account object
   */
  Account updateAccount(Account account);

  /**
   * Gets a list with Account objects for a specific user.
   * 
   * @param user the identifier for the accounts
   * @return a list with Account objects
   * @throws AccountNotFound if the account can not be found
   */
  List<Account> getAccountsNo(User user) throws AccountNotFound;
  
}
