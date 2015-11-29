package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.UserDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserRegisteredException;

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
   * @throws UserRegisteredException
   */
  void insertUser(UserDto user) throws UserRegisteredException;

  /**
   * Gets an user by an id.
   * 
   * @param id the identifier for the user
   * @return the User object with the given id
   * @throws UserNotFound if the user could not be found
   */
  UserDto getUserbyId(Integer id) throws UserNotFound;

  /**
   * Gets an user by its username
   * 
   * @param username the username of the user
   * @return the user with the given username
   * @throws UserNotFound if the user could not be found
   */
  UserDto getUserByUsername(String username) throws UserNotFound;

  /**
   * Gets an user by an Account object.
   * 
   * @param account the identifier
   * @return the user which has the specified account
   * @throws UserNotFound if the user could not be found
   */
  UserDto getUserByAccount(AccountDetailsDto accountDetails) throws UserNotFound;

}
