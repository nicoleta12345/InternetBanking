package com.iquest.advancedframeworks.internetbanking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserRole;

/**
 * The CustomerDaoImpl class implements CustomerDao interface and extends the abstract class GenericDaoImpl taking
 * benefits of its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

  /**
   * EntityManager is used to do operation on the database.
   */
  @PersistenceContext
  EntityManager entityManager;

  @Override
  public void create(User t) {
    entityManager.persist(t.getUserDetails());
    entityManager.persist(t.getUserDetails().getAddress());
    entityManager.persist(t);
  }

  @Override
  public User getUserByAccount(Account account) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);

    Root<User> userRoot = cq.from(User.class);

    cq.select(userRoot).where(cb.equal(userRoot.join("accounts").get("id"), account.getAccountId()));

    Query q = entityManager.createQuery(cq);

    User result = (User) q.getSingleResult();

    return result;
  }

  @Override
  public List<Account> getAccountsNo(User user) {
    System.out.println("in query " + user.getId());
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Account> cq = cb.createQuery(Account.class);

    Root<Account> accountRoot = cq.from(Account.class);
    //Root<User> userRoot = cq.from(User.class);

    cq.select(accountRoot).where(cb.equal(accountRoot.join("user").get("id"), user.getId()));

    Query q = entityManager.createQuery(cq);

    List<Account> result = (List<Account>) q.getResultList();

    return result;
  }

  @Override
  public User getUserByUsername(String username) {
    System.out.println("get user by username");
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);

    Root<User> userRoot = cq.from(User.class);

    cq.select(userRoot).where(cb.equal(userRoot.get("username"), username));

    Query q = entityManager.createQuery(cq);

    User result = (User) q.getSingleResult();
    System.out.println("User searched" + result);

    return result;

  }

  @Override
  public List<UserRole> getUserRoles(User user) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);

    Root<UserRole> userRoleRoot = cq.from(UserRole.class);
    Root<User> userRoot = cq.from(User.class);

    cq.select(userRoleRoot).where(cb.equal(userRoot.get("id"), user.getId()));

    Query q = entityManager.createQuery(cq);

    List<UserRole> result = q.getResultList();

    System.out.println(result);
    return result;
  }

}
