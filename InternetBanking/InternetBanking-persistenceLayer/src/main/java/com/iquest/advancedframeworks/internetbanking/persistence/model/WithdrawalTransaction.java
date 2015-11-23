package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * /** The TransferTransaction class represents a transaction of type
 * withdrawal.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "transaction_withdrawal")
public class WithdrawalTransaction extends Transaction implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  /**
   * The sender account.
   */
  @OneToOne
  @JoinColumn(name = "sender_idAccount")
  @NotNull
  private Account senderAccount;

  public Account getSenderAccount() {
    return senderAccount;
  }

  public void setSenderAccount(Account senderAccount) {
    this.senderAccount = senderAccount;
  }

}
