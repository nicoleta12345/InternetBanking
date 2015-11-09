package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquest.advancedframeworks.internetbanking.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.model.Address;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserDetails;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

/**
 * The UserServiceImpl class represents a service which use a UserDao injected
 * object to perform operations with User objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * An UserDao injected object used to make operations with the User objects
	 * into the database.
	 */
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserbyId(Integer id) {
		return userDao.readUser(id);
	}

	@Override
	public void insertUser(User user, UserDetails userDetails, Address address) {
		userDao.createUser(user, userDetails, address);

	}

}
