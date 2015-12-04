package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * The DepositTransaction class represents a transaction of type deposit.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("Deposit")
public class Deposit extends Transaction implements Serializable {

  /**
	 * The serial version.
	 */
  private static final long serialVersionUID = 1L;

  /**
   * The receiver account.
   */
  @OneToOne
  @JoinColumn(name = "RECEIVER_ACCOUNT_ID")
  private Account receiverAccount;

  public Account getReceiverAccount() {
    return receiverAccount;
  }

  public void setReceiverAccount(Account receiverAccount) {
    this.receiverAccount = receiverAccount;
  }

}
