package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The DepositTransaction class represents a transaction of type deposit.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "TRANSACTION_DEPOSIT")
public class DepositTransaction extends Transaction implements Serializable {

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
