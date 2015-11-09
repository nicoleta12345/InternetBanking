package com.iquest.advancedframeworks.internetbanking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The User class represents a customer for an online banking application. Due
 * to @Entity annotation there will be generated a table for this class into a
 * database.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "user")
public class User {

	/**
	 * The id of the user. It represents the primary key for the table which
	 * will be generated.
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * The user code used for authentication.
	 */
	@NotNull
	private String userCode;

	/**
	 * The password used for authentication.
	 */
	@NotNull
	private String password;

	/**
	 * The user details. Into the database the userdetails primary key(id) will
	 * be set as a foreign key into the table generated for this class.
	 */
	@OneToOne
	@JoinColumn(name = "userDetails_Id")
	private UserDetails userDetails;

	/**
	 * The user accounts. Into the database the account primary key(id) will be
	 * set as a foreign key into the table generated for this class.
	 */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user_Id") , inverseJoinColumns = @JoinColumn(name = "account_Id") )
	private List<Account> accounts = new ArrayList<>();

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
