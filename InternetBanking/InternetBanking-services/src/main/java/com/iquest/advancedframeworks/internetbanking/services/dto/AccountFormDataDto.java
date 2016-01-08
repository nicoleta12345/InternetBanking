package com.iquest.advancedframeworks.internetbanking.services.dto;

import java.util.List;

/**
 * The AccountFormDataDto class keeps the details which will be displayed into a from related to accounts.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class AccountFormDataDto {

  /**
   * The user accounts.
   */
  private List<AccountDetailsDto> userAccounts;

  public List<AccountDetailsDto> getUserAccounts() {
    return userAccounts;
  }

  public void setUserAccounts(List<AccountDetailsDto> userAccounts) {
    this.userAccounts = userAccounts;
  }

}
