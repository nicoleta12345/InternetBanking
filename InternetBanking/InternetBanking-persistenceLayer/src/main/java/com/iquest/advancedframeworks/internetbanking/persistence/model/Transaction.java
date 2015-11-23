package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * The Transaction class contains common attributes and functionalities of an
 * operation of transaction.
 * 
 * @author Lucian
 *
 */
@MappedSuperclass
public class Transaction {

  /**
   * The id of the transaction.
   */
  @Id
  @GeneratedValue
  private int id;

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
