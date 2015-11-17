package com.iquest.advancedframeworks.internetbanking.services;

import java.util.List;

import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.Address;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserDetails;

/**
 * The UserService interface represents a service which can do operations with
 * User objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserService {

	/**
	 * Calls methods from a repository to insert a new User into a database.
	 * 
	 * @param user
	 *            the information which will be inserted into the new entry
	 * @param userDetails
	 *            the user details
	 * @param address
	 *            the user address
	 */
	void insertUser(User user, UserDetails userDetails, Address address);

	/**
	 * Gets an user by an id.
	 * 
	 * @param id
	 *            the identifier for the user
	 * @return the User object with the given id
	 */
	User getUserbyId(Integer id);

	User getUserByAccount(Account sender);

  List<Account> getAccountsNo(User user);

}
