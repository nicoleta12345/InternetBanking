package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.model.Address;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserDetails;

/**
 * The UserDaoImpl class implements UserDao interface.
 * It makes CRUD operations on User objects.
 *  
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	/**
	 * EntityManager is used to do operation on the database.
	 */
	@PersistenceContext
	EntityManager entityManager;


	@Override
	@Transactional
	public User readUser(Integer id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	@Transactional
	public void createUser(User user, UserDetails userDetails, Address address) {
		this.entityManager.persist(user);
		this.entityManager.persist(userDetails);
		this.entityManager.persist(address);
		
		this.entityManager.flush();		
	}

	/**
	 * Not yet implemented.
	 */
	@Override
	@Transactional
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Not yet implemented.
	 */
	@Override
	@Transactional
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
