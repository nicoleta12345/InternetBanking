package com.iquest.advancedframeworks.internetbanking.persistence.dao;

import java.util.List;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserDao interface adds operations which can be performed with User entities besides the CRUD operations inherited
 * from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserDao extends GenericDao<User> {

  /**
   * Gets a user using as an identifier a number of an account.
   * 
   * @param account the Account object which identifies the user
   * @return the user identified by it's account
   */
  User getUserByAccount(Account account);

  /**
   * Gets a user using as an identifier the username.
   * 
   * @param username the identifier of the user
   * @return the user identified by it's username
   */
  User getUserByUsername(String username);

  /**
   * Gets the roles of an user.
   * 
   * @param user the User object used
   * @return a list with the user roles
   */
  List<UserRole> getUserRoles(User user);
}
