package com.iquest.advancedframeworks.internetbanking.services.dto;

/**
 * The TransactionDto class is a base DTO class for different types of transactions.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class TransactionDto {

  /**
   * The value of the transaction.
   */
  private double value;

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

}
