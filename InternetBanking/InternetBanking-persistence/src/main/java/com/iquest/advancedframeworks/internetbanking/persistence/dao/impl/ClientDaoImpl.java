package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.ClientDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;

/**
 * The ClientDaoImpl class implements ClientDao interface and extends the abstract class GenericDaoImpl taking benefits of
 * its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class ClientDaoImpl extends GenericDaoImpl<Client> implements ClientDao {

  /**
   * EntityManager is used to do operation on the database.
   */
  @PersistenceContext
  EntityManager entityManager;

  @Override
  public void create(Client t) throws EntityRegisteredException {
    try {
      entityManager.persist(t.getAddress());
      entityManager.persist(t);
    } catch (EntityExistsException e) {
      throw new EntityRegisteredException("The user already exists into the database!");
    }
  }

  @Override
  public Client getClientByAccount(Account account) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Client> cq = cb.createQuery(Client.class);
    Root<Client> clientRoot = cq.from(Client.class);
    cq.select(clientRoot).where(cb.equal(clientRoot.join("accounts").get("id"), account.getAccountId()));
    Query q = entityManager.createQuery(cq);

    Client result = null;
    try {
      result = (Client) q.getSingleResult();
    } catch (NoResultException e) {
      // stay silent, null will be returned
    }

    return result;
  }

}
