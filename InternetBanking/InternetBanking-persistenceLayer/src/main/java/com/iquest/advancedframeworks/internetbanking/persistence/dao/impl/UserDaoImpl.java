package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserDaoImpl class implements UserDao interface and extends the abstract class GenericDaoImpl taking benefits of
 * its methods and adding more specific ones.
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
  public void create(User t) throws EntityRegisteredException {
    try {
      entityManager.persist(t.getUserDetails());
      entityManager.persist(t.getUserDetails().getAddress());
      entityManager.persist(t);
    } catch (EntityExistsException e) {
      throw new EntityRegisteredException("The user already exists into the database!");
    }
  }

  @Override
  public User getUserByAccount(Account account) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> userRoot = cq.from(User.class);
    cq.select(userRoot).where(cb.equal(userRoot.join("accounts").get("id"), account.getAccountId()));
    Query q = entityManager.createQuery(cq);

    User result = null;
    try {
      result = (User) q.getSingleResult();
    } catch (NoResultException e) {
      // do nothing, null will be returned
    }

    return result;
  }

  @Override
  public User getUserByUsername(String username) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> userRoot = cq.from(User.class);
    cq.select(userRoot).where(cb.equal(userRoot.get("username"), username));
    Query q = entityManager.createQuery(cq);

    User result = null;
    try {
      result = (User) q.getSingleResult();
    } catch (NoResultException e) {
      // do nothing, null will be returned
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<UserRole> getUserRoles(User user) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);
    Root<UserRole> userRoleRoot = cq.from(UserRole.class);
    Root<User> userRoot = cq.from(User.class);
    cq.select(userRoleRoot).where(cb.equal(userRoot.get("id"), user.getId()));
    Query q = entityManager.createQuery(cq);

    List<UserRole> result = q.getResultList();

    return result;
  }

}
