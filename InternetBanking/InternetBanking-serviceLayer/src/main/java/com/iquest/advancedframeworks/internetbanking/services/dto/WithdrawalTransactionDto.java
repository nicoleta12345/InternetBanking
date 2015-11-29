package com.iquest.advancedframeworks.internetbanking.services.dto;

/**
 * The WithdrawalTransactionDto class represents a DTO which contains details about a withdrawal transaction.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class WithdrawalTransactionDto extends TransactionDto {

  /**
   * The sender account number.
   */
  private String senderAccountNumber;

  public String getSenderAccountNumber() {
    return senderAccountNumber;
  }

  public void setSenderAccountNumber(String senderAccountNumber) {
    this.senderAccountNumber = senderAccountNumber;
  }

}
