package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.DepositTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.DepositTransaction;

/**
 * The DepositTransactionDaoImpl class implements DepositTransactionDao
 * interface and extends the abstract class GenericDaoImpl taking benefits of
 * its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class DepositTransactionDaoImpl extends
    GenericDaoImpl<DepositTransaction> implements DepositTransactionDao {

  /**
   * EntityManager is used to do operations with the database.
   */
  @PersistenceContext
  private EntityManager entityManager;

}
