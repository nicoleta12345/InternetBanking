package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The CreditAccount class represents a specific type of account. It has a cost of maintenance for every month.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("CreditAccount")
public class CreditAccount extends Account {

  /**
   * The mentanance cost of the account.
   */
  @Column(name = "MENTENANCE_COST")
  private Double mentenanceCost;

  public Double getMentenanceCost() {
    return mentenanceCost;
  }

  public void setMentenanceCost(Double mentenanceCost) {
    this.mentenanceCost = mentenanceCost;
  }

}
