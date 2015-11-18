package com.iquest.advancedframeworks.internetbanking.dao;

import java.util.List;

import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.User;

/**
 * The UserDao interface adds operations which can be performed with User
 * entities besides the CRUD operations inherited from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserDao extends GenericDao<User> {

  /**
   * Gets a user using as an identifier a number of an account.
   * 
   * @param account
   *          the Account object which identifies the user
   * @return the user identified by it's account
   */
  User getUserByAccount(Account account);

  /**
   * Gets the number of the account of a specific user.
   * 
   * @param user the user for which the accounts are requested
   * @return a List with the user Accounts
   */
  List<Account> getAccountsNo(User user);

}
