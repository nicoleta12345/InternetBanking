package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.TransferTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.TransferTransaction;

@Repository
public class TransferTransactionDaoImpl implements TransferTransactionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createTransaction(TransferTransaction transferTransaction) {
		entityManager.persist(transferTransaction);
	}

	@Override
	public TransferTransaction readTransaction(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferTransaction updateTransaction(TransferTransaction transferTransaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTransaction(TransferTransaction transferTransaction) {
		// TODO Auto-generated method stub

	}

}
