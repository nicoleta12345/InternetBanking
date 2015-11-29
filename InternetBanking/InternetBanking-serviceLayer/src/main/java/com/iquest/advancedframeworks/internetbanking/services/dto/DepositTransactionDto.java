package com.iquest.advancedframeworks.internetbanking.services.dto;

/**
 * The DepositTransactionDto class is a DTO which contains information about a deposit transaction.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class DepositTransactionDto extends TransactionDto {

  /**
   * The receiver account number.
   */
  private String receiverAccountNumber;

  public String getReceiverAccountNumber() {
    return receiverAccountNumber;
  }

  public void setReceiverAccountNumber(String receiverAccountNumber) {
    this.receiverAccountNumber = receiverAccountNumber;
  }

}
