package com.iquest.advancedframeworks.internetbanking.dao;

import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The AccountDao interface specifies the basic CRUD operation which can be
 * performed with Account entities.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface AccountDao {

	/**
	 * Creates a new account entry into the database.
	 * 
	 * @param account
	 *            the object which contain the information which will be stored
	 *            into the database.
	 */
	void createAccount(Account account);

	/**
	 * Reads an account from the database.
	 * 
	 * @param id
	 *            the primary key of the account
	 * @return the Account object
	 */
	Account readAccount(Integer id);

	/**
	 * Updates an account entry from the database.
	 * 
	 * @param account
	 *            the Account object with the updated information
	 * @return the Account object updated
	 */
	Account updateAccount(Account account);

	/**
	 * Deletes an account entry from the database.
	 * 
	 * @param account
	 *            the Account object which identifies the entry from the
	 *            database which will be deleted.
	 */
	void deleteAccount(Account account);

}
