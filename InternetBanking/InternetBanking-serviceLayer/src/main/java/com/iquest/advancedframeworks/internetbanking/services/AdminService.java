package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.UserDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserRegisteredException;

/**
 * The AdminService interface declares methods which represents services for the admin.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface AdminService {

  /**
   * Registers a new client.
   * 
   * @param clientDto the details about the new client
   * @throws UserRegisteredException if the client is already registered
   */
  public void registerNewClient(UserDto clientDto) throws UserRegisteredException;

  /**
   * Registers a new account.
   * 
   * @param accountDetails the details about the new account
   * @param clientIdentifier the identifier of the owner for the account
   * @throws AccountRegisteredException if the account is already registered
   */
  void registerNewAccount(AccountDetailsDto accountDetails, String accountType, String clientIdentifier) throws AccountRegisteredException;

}
