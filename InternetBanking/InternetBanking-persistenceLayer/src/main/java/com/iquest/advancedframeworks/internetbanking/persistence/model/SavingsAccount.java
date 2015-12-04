package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The SavingsAccount class represents a specific type of account. It has a interest for every month.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("SavingsAccount")
public class SavingsAccount extends Account {

  /**
   * The interest percentage.
   */
  @Column(name = "INTEREST_PERCENTAGE", columnDefinition = "double default 3")
  private Integer interestPercentage;

  public Integer getInterestPercentage() {
    return interestPercentage;
  }

  public void setInterestPercentage(Integer interestPercentage) {
    this.interestPercentage = interestPercentage;
  }

}
