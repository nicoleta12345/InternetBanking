package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.SavingsAccount;

/**
 * The AccountDaoImpl class implements AccountDao interface and extends the abstract class GenericDaoImpl taking
 * benefits of its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {

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

    Account result = null;
    try {
      result = (Account) q.getSingleResult();
    } catch (NoResultException e) {
      // stay silent, null will be returned
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<SavingsAccount> getAllSavingsAccounts() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<SavingsAccount> cq = cb.createQuery(SavingsAccount.class);
    Root<SavingsAccount> root = cq.from(SavingsAccount.class);
    cq.select(root);
    Query q = entityManager.createQuery(cq);

    List<SavingsAccount> result = new ArrayList<>();
    try {
      result = (List<SavingsAccount>) q.getResultList();
    } catch (NoResultException e) {
      // stay silent, null will be returned
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Account> getAllAccounts() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Account> cq = cb.createQuery(Account.class);
    Root<Account> root = cq.from(Account.class);
    cq.select(root);
    Query q = entityManager.createQuery(cq);

    List<Account> result = new ArrayList<>();
    try {
      result = (List<Account>) q.getResultList();
    } catch (NoResultException e) {
      // stay silent, null will be returned
    }

    return result;
  }

}
