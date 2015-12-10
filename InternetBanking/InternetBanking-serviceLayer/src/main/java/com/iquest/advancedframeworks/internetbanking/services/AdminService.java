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
   * @param accountRegistrationDto the details about the new account
   * @param accountType the type of the account
   * @throws AccountRegisteredException if the account is already registered
   */
  void registerNewAccount(AccountDetailsDto accountRegistrationDto, String accountType) throws AccountRegisteredException;

}
