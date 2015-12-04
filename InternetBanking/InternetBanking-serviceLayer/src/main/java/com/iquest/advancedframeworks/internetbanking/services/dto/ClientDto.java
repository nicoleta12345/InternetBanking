package com.iquest.advancedframeworks.internetbanking.services.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The ClientDto class represents a DTO which contains details about a user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class ClientDto extends UserDto {

  /**
   * The user accounts. Into the database the account primary key(id) will be set as a foreign key into the table
   * generated for this class.
   */
  private List<AccountDetailsDto> accounts = new ArrayList<>();

  public List<AccountDetailsDto> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<AccountDetailsDto> accounts) {
    this.accounts = accounts;
  }

}
