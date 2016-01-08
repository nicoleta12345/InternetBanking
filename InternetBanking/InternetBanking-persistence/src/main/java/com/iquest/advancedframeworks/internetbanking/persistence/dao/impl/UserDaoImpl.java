package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;

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
      if (t.getAddress() != null) {
        entityManager.persist(t.getAddress());
      }
      entityManager.persist(t);
    } catch (EntityExistsException e) {
      throw new EntityRegisteredException("The user already exists into the database!");
    }
  }

  @Override
  public User getUserByUsername(String username) {
    Object result = null;

    try {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<User> cq = cb.createQuery(User.class);
      Root<User> root = cq.from(User.class);
      cq.where(cb.equal(root.get("username"), username));
      Query q = entityManager.createQuery(cq);

      result = q.getSingleResult();
    }
    catch (NoResultException e) {
      // stay silent, null will be returned
    }
    catch (PersistenceException e) {
      // throw new PersistenceFailure("Persistence Failure!");
    }

    return (User) result;
  }

  @Override
  public User getUserByCnp(String cnp) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Client> cq = cb.createQuery(Client.class);
    Root<Client> clientRoot = cq.from(Client.class);
    cq.select(clientRoot).where(cb.equal(clientRoot.get("cnp"), cnp));
    Query q = entityManager.createQuery(cq);

    Object result = null;
    try {
      result = q.getSingleResult();
    }
    catch (NoResultException e) {
      // stay silent, null will be returned
    }

    return (User) result;
  }

}
