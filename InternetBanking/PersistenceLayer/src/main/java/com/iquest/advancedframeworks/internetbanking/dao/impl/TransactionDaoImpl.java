package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.TransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void createTransaction(Transaction transaction) {
		this.entityManager.persist(transaction);
		
	}

	@Override
	public Transaction readTransaction(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}

}
