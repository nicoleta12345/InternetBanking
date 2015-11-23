package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;

/**
 * The UserService interface represents a service which can do operations with User objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserService {

  /**
   * Calls methods from a repository to insert a new User into a database.
   * 
   * @param user the information which will be inserted into the new entry
   * @param userDetails the user details
   * @param address the user address
   */
  void insertUser(User user);

  /**
   * Gets an user by an id.
   * 
   * @param id the identifier for the user
   * @return the User object with the given id
   */
  User getUserbyId(Integer id);

  /**
   * Gets an user by its username
   * 
   * @param username the username of the user
   * @return the user with the given username
   */
  User getUserByUsername(String username);

  /**
   * Gets an user by an Account object.
   * 
   * @param account the identifier
   * @return the user which has the specified account
   */
  User getUserByAccount(Account account);

}
