package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The TransferTransaction class represents specific type of transaction, a transaction from an account to another.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "TRANSACTION_TRANSFER")
public class TransferTransaction extends Transaction implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * The sender account.
   */
  @OneToOne
  @JoinColumn(name = "SENDER_ACCOUNT_ID")
  @NotNull
  private Account senderAccount;

  /**
   * The receiver account.
   */
  @OneToOne
  @JoinColumn(name = "receiver_idAccount")
  private Account receiverAccount;

  public Account getSenderAccount() {
    return senderAccount;
  }

  public void setSenderAccount(Account senderAccount) {
    this.senderAccount = senderAccount;
  }

  public Account getReceiverAccount() {
    return receiverAccount;
  }

  public void setReceiverAccount(Account receiverAccount) {
    this.receiverAccount = receiverAccount;
  }

}
