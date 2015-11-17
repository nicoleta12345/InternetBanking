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
	private String accountNumber;

	/**
	 * The amount of the account.
	 */
	private double amount;

	public Account() {};
	
	public Account(String accountNumber) {
	  this.accountNumber = accountNumber;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		StringBuilder account = new StringBuilder();
		String separator = " ";
		
		account.append(accountId);
		account.append(separator);
		
		account.append(accountNumber);
		account.append(separator);
		
		account.append(amount);

		return account.toString();
	}

}
