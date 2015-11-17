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
 * The AccountDaoImpl class implements AccountDao interface. It makes CRUD
 * operations on Account objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class AccountDaoImpl implements AccountDao {

  /**
   * EntityManager is used to do operation on the database.
   */
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void createAccount(Account account) {
    this.entityManager.persist(account);
    this.entityManager.flush();
  }

  @Override
  public Account readAccount(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account updateAccount(Account account) {
    Account accountUpdated = this.entityManager.merge(account);
    this.entityManager.flush();

    System.out.println("update account");
    return accountUpdated;
  }

  @Override
  public void deleteAccount(Account account) {
    // TODO Auto-generated method stub

  }

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
