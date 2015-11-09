package com.iquest.advancedframeworks.internetbanking.dao;

import com.iquest.advancedframeworks.internetbanking.model.Address;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserDetails;

/**
 * The UserDao interface specifies the CRUD operations which can be performed on
 * User objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserDao {

	/**
	 * Creates an entry for an User object into the database.
	 * 
	 * @param user
	 *            the User which informations represents a new entry into the
	 *            database
	 * @param userDetails the details of an user
	 * @param address the address of an user
	 */
	void createUser(User user, UserDetails userDetails, Address address);

	/**
	 * Reads a User object from the database.
	 * 
	 * @param id
	 *            the primary key for the user
	 * @return the User object populated with the details from the database
	 */
	User readUser(Integer id);

	/**
	 * Updates a User entry into the database with the information from the User
	 * object received as a parameter.
	 * 
	 * @param user
	 *            the updated information which will updated the corresponding
	 *            entry into the database
	 * @return an User object with the information updated from the database
	 */
	User updateUser(User user);

	/**
	 * Deletes a user entry from the database.
	 * 
	 * @param user
	 *            the User object which contains the information needed to
	 *            identify the entry from the database which will be deleted
	 */
	void deleteUser(User user);

}
