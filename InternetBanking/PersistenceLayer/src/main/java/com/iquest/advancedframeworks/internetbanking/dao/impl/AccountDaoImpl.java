package com.iquest.advancedframeworks.internetbanking.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.model.Account;

/**
 * The AccountDaoImpl class implements AccountDao interface and extends the
 * abstract class GenericDaoImpl taking benefits of its methods and adding more
 * specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account> implements
    AccountDao {

  /**
   * EntityManager is used to do operations with the database.
   */
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Account getAccountByNo(String accountNumber) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Account> cq = cb.createQuery(Account.class);
    Root<Account> root = cq.from(Account.class);
    cq.select(root).where(cb.equal(root.get("accountNumber"), accountNumber));

    Query q = entityManager.createQuery(cq);

    Account result = (Account) q.getSingleResult();

    return result;
  }

}
