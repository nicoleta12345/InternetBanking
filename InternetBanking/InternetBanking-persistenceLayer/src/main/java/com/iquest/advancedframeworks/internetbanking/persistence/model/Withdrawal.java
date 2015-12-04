package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * The TransferTransaction class represents a transaction of type withdrawal.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("Withdrawal")
public class Withdrawal extends Transaction implements Serializable {

  /**
   * The serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The sender account.
   */
  @OneToOne
  @JoinColumn(name = "SENDER_ACCOUNT_ID")
  @NotNull
  private Account senderAccount;

  public Account getSenderAccount() {
    return senderAccount;
  }

  public void setSenderAccount(Account senderAccount) {
    this.senderAccount = senderAccount;
  }

}
