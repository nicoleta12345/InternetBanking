package com.iquest.advancedframeworks.internetbanking.dao;

import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The AccountDao interface adds operations which can be performed with Account
 * entities besides the CRUD operations inherited from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface AccountDao extends GenericDao<Account> {

  /**
   * Gets an account by its number.
   * 
   * @param accountNo
   *          the identifier of the Account object
   * @return the Account object with the specified identifier
   */
  Account getAccountByNo(String accountNo);

}
