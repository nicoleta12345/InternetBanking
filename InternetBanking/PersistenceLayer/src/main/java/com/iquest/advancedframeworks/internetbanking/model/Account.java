package com.iquest.advancedframeworks.internetbanking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Account class represent an account of an user. It will be generated a
 * table into the database with the specified name and the fields of this class
 * as attributes.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "account")
public class Account {

	/**
	 * The id of the account. It represents the primary key for the account
	 * table. It is a generated value.
	 */
	@Id
	@GeneratedValue
	private int accountId;

	/**
	 * The number of the account.
	 */
	private Long accountNumber;

	/**
	 * The amount of the account.
	 */
	private Double amount;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
