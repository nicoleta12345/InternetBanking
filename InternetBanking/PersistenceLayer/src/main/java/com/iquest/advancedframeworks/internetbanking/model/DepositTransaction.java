package com.iquest.advancedframeworks.internetbanking.model;

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
@Table(name = "transaction_deposit")
public class DepositTransaction extends Transaction implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  /**
   * The receiver account.
   */
  @OneToOne
  @JoinColumn(name = "receiver_idAccount")
  private Account receiverAccount;

  public Account getReceiverAccount() {
    return receiverAccount;
  }

  public void setReceiverAccount(Account receiverAccount) {
    this.receiverAccount = receiverAccount;
  }

}
