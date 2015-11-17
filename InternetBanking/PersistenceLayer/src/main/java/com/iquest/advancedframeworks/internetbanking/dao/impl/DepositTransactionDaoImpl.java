package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.DepositTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.DepositTransaction;

@Repository
public class DepositTransactionDaoImpl implements DepositTransactionDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void createTransaction(DepositTransaction depositTransaction) {
    entityManager.persist(depositTransaction);
  }

  @Override
  public DepositTransaction readTransaction(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DepositTransaction updateTransaction(
      DepositTransaction depositTransaction) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteTransaction(DepositTransaction depositTransaction) {
    // TODO Auto-generated method stub

  }

}
