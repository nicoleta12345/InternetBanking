package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Transaction;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Transfer;

/**
 * The TransactionDaoImpl class implements TransactionDao interface and extends the abstract class GenericDaoImpl taking
 * benefits of its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao {

  /**
   * EntityManager is used to do operation on the database.
   */
  @PersistenceContext
  EntityManager entityManager;

  @Override
  public List<Transfer> getPendingTransactions() {
    List<Transfer> result = null;

    try {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Transfer> cq = cb.createQuery(Transfer.class);
      Root<Transfer> root = cq.from(Transfer.class);
      cq.where(cb.equal(root.get("pending"), 1));
      Query q = entityManager.createQuery(cq);

      result = q.getResultList();
    }
    catch (NoResultException e) {
      // stay silent, null will be returned
    }
    catch (PersistenceException e) {
      // throw new PersistenceFailure("Persistence Failure!");
    }

    return result;
  }

}
