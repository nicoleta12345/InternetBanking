package com.iquest.advancedframeworks.internetbanking.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_deposit")
public class DepositTransaction extends Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue
//	private int id;

	@OneToOne
	@JoinColumn(name = "receiver_idAccount")
	private Account receiverAccount;

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
	
//	public double getValue() {
//		return super.getValue();
//	}
//
//	public void setValue(double value) {
//		super.setValue(value);
//	}	

}
