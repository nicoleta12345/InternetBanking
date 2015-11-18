package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.TransferTransactionDao;
import com.iquest.advancedframeworks.internetbanking.model.TransferTransaction;

/**
 * The TransferTransactionDaoImpl class implements TransferTransactionDao
 * interface and extends the abstract class GenericDaoImpl taking benefits of
 * its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class TransferTransactionDaoImpl extends
    GenericDaoImpl<TransferTransaction> implements TransferTransactionDao {

  /**
   * EntityManager is used to do operations with the database.
   */
  @PersistenceContext
  private EntityManager entityManager;

}
