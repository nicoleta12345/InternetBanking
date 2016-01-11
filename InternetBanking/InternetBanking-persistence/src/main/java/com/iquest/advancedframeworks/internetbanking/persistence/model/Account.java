package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * The abstract class Account represents an account of an user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "ACCOUNTS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Account {

  /**
   * The id of the account. It represents the primary key for the account table. It is a generated value.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  @Column(name = "START_DATE")
  private Date startDate;
  /**
   * The number of the account.
   */
  @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
  private String accountNumber;

  /**
   * The amount of the account.
   */
  @Column(name = "AMOUNT", nullable = false)
  private Double amount;

  /**
   * The blocked amount of the account.
   */
  @Column(name = "BLOCKED_AMOUNT", columnDefinition = "double default 0.0")
  private Double blockedAmount;

  /**
   * The maintenance cost of the account.
   */
  @Column(name = "MENTENANCE_COST")
  private Double maintenanceCost;

  /**
   * Default constructor.
   */
  public Account() {
  };

  public Double getMentenanceCost() {
    return maintenanceCost;
  }

  public void setMentenanceCost(Double mentenanceCost) {
    this.maintenanceCost = mentenanceCost;
  }

  public Account(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Double getBlockedAmount() {
    return blockedAmount;
  }

  public void setBlockedAmount(Double blockedAmount) {
    this.blockedAmount = blockedAmount;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public int getAccountId() {
    return id;
  }

  public void setAccountId(int accountId) {
    this.id = accountId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
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
