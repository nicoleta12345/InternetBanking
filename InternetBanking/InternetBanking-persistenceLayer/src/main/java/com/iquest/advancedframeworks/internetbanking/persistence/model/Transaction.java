package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

/**
 * The abstract class Transaction contains common attributes and functionalities of an operation of transaction.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "TRANSACTIONS")
@DiscriminatorOptions(force = true)
public class Transaction {

  /**
   * The id of the transaction.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The value of the transaction.
   */
  @Column(name = "VALUE", nullable = false)
  private double value;

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

}
