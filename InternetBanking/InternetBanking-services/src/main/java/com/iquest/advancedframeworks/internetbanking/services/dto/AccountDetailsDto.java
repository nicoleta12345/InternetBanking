package com.iquest.advancedframeworks.internetbanking.services.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The AccountDetailsDto class represents a DTO which contains details about an Account object.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class AccountDetailsDto {

  /**
   * The number of the account.
   */
  @NotEmpty(message = "{accountRegistrationForm.accountNumberRequired}")
  private String accountNumber;

  /**
   * The amount of the account.
   */
  @NotNull(message = "{accountRegistrationForm.amountRequired}")
  private double amount;

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "AccountDetailsDto [accountNumber=" + accountNumber + ", amount=" + amount + "]";
  }

}
