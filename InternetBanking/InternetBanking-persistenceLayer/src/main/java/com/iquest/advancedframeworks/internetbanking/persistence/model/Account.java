package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Account class represent an account of an user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {

  /**
   * The id of the account. It represents the primary key for the account table. It is a generated value.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The number of the account.
   */
  @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
  private String accountNumber;

  /**
   * The amount of the account.
   */
  @Column(name = "AMOUNT", nullable = false)
  private double amount;

  /**
   * Default constructor.
   */
  public Account() {
  };

  public Account(String accountNumber) {
    this.accountNumber = accountNumber;
  }

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

  public int getAccountId() {
    return id;
  }

  public void setAccountId(int accountId) {
    this.id = accountId;
  }

  @Override
  public String toString() {
    StringBuilder account = new StringBuilder();
    String separator = " ";

    account.append(id);
    account.append(separator);

    account.append(accountNumber);
    account.append(separator);

    account.append(amount);

    return account.toString();
  }

}
