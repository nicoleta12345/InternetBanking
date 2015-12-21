package com.iquest.advancedframeworks.internetbanking.services.dto;

/**
 * The TransferTransactionDto class is a DTO which contains details about a transfer transaction.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class TransferTransactionDto extends TransactionDto {

  /**
   * The sender account number.
   */
  private String senderAccountNumber;

  /**
   * The receiver account number.
   */
  private String externalAccountNumber;

  private String receiverAccountNumber;

  public String getSenderAccountNumber() {
    return senderAccountNumber;
  }

  public void setSenderAccountNumber(String senderAccountNumber) {
    this.senderAccountNumber = senderAccountNumber;
  }

  public String getExternalAccountNumber() {
    return externalAccountNumber;
  }

  public void setExternalAccountNumber(String externalAccountNumber) {
    this.externalAccountNumber = externalAccountNumber;
  }

  @Override
  public String toString() {
    return "TransferTransactionDto [senderAccountNumber=" + senderAccountNumber + ", externalAccountNumber="
        + externalAccountNumber + ", getValue()=" + getValue() + ", getPending()=" + getPending() + "]";
  }

  public String getReceiverAccountNumber() {
    return receiverAccountNumber;
  }

  public void setReceiverAccountNumber(String receiverAccountNumber) {
    this.receiverAccountNumber = receiverAccountNumber;
  }

}
