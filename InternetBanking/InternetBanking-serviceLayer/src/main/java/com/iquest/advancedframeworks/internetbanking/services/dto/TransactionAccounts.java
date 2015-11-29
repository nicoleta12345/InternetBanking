package com.iquest.advancedframeworks.internetbanking.services.dto;

import java.util.List;

/**
 * The TransactionAccounts class represents the details about the account used for transactions.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class TransactionAccounts {

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

  @Override
  public String toString() {
    return "TransactionAccounts [userAccounts=" + userAccounts + "]";
  }

}
