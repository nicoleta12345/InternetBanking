package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The AccountDaoImpl class implements AccountDao interface.
 * It makes CRUD operations on Account objects.
 *  
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class AccountDaoImpl implements AccountDao {

	/**
	 * EntityManager is used to do operation on the database.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void createAccount(Account account) {
		this.entityManager.persist(account);
		this.entityManager.flush();
	}

	@Override
	@Transactional
	public Account readAccount(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
	
}
