package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * The TransferTransaction class represents specific type of transaction, a transaction from an account to another.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("Transfer")
public class Transfer extends Transaction implements Serializable {

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
  @JoinColumn(name = "RECEIVER_ACCOUNT_ID")
  private Account receiverAccount;

  /**
   * The external account number.
   */
  @Column(name = "EXTERNAL_ACCOUNT")
  private String externalAccountNumber;

  /**
   * This field is set to true if the receiver account is external. It change its state when the transaction is approved
   * or declined.
   */
  @Column(name = "PENDING")
  private Boolean pending;

  public String getExternalAccountNumber() {
    return externalAccountNumber;
  }

  public void setExternalAccountNumber(String externalAccountNumber) {
    this.externalAccountNumber = externalAccountNumber;
  }

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

  public Boolean getPending() {
    return pending;
  }

  public void setPending(Boolean pending) {
    this.pending = pending;
  }

  @Override
  public String toString() {
    return "Transfer [senderAccount=" + senderAccount + ", receiverAccount=" + receiverAccount
        + ", externalAccountNumber=" + externalAccountNumber + ", pending=" + pending + "]";
  }

}
