package com.iquest.advancedframeworks.internetbanking.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transaction_transfer")
public class TransferTransaction extends Transaction implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @OneToOne
  @JoinColumn(name = "sender_idAccount")
  @NotNull
  private Account senderAccount;

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
