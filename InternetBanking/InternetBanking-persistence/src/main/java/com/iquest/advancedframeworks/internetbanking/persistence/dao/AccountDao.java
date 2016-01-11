package com.iquest.advancedframeworks.internetbanking.persistence.dao;

import java.util.List;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.SavingsAccount;

/**
 * The AccountDao interface adds operations which can be performed with Account entities besides the CRUD operations
 * inherited from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface AccountDao extends GenericDao<Account> {

  /**
   * Gets an Account object identified by its number.
   * 
   * @param accountNo the identifier of the Account object
   * @return the Account object with the specified identifier
   */
  Account getAccountByNo(String accountNo);

  /**
   * Gets all Savings accounts.
   * 
   * @return a list whith all savings accounts
   */
  List<SavingsAccount> getAllSavingsAccounts();

  /**
   * Gets all accounts.
   * 
   * @return a list with all accounts
   */
  List<Account> getAllAccounts();

}
