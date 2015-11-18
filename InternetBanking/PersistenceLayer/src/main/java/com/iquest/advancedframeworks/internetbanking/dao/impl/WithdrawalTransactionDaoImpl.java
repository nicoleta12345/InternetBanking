package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.WithdrawalTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.WithdrawalTransaction;

/**
 * The WithdrawalTransactionDaoImpl class implements WithdrawalTransactionDao
 * interface and extends the abstract class GenericDaoImpl taking benefits of
 * its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class WithdrawalTransactionDaoImpl extends
    GenericDaoImpl<WithdrawalTransaction> implements WithdrawalTransactionDao {

  /**
   * EntityManager is used to do operation on the database.
   */
  @PersistenceContext
  private EntityManager entityManager;

}
