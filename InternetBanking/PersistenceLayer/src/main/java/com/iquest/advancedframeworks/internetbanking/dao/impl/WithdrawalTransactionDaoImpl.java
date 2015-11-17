package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.WithdrawalTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.WithdrawalTransaction;

@Repository
public class WithdrawalTransactionDaoImpl implements WithdrawalTransactionDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void createTransaction(WithdrawalTransaction withdrawalTransaction) {
    entityManager.persist(withdrawalTransaction);
  }

  @Override
  public WithdrawalTransaction readTransaction(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public WithdrawalTransaction updateTransaction(
      WithdrawalTransaction withdrawalTransaction) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteTransaction(WithdrawalTransaction withdrawalTransaction) {
    // TODO Auto-generated method stub

  }

}
