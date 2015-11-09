package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The AccountService interface represents a service which operates with Account objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface AccountService {

	/**
	 * Calls certain methods to insert a new entry into the database for a
	 * certain Account object.
	 * 
	 * @param account
	 *            the account which will be inserted into the database
	 */
	void createAccount(Account account);
}
