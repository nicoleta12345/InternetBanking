package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserRoleDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Role;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserRoleDaoImpl class implements UserRoleDao interface and extends the abstract class GenericDaoImpl taking
 * benefits of its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao {

  /**
   * EntityManager is used to do operation on the database.
   */
  @PersistenceContext
  EntityManager entityManager;

  @Override
  public UserRole getUserRolebyRole(Role role) {
    UserRole result = null;
    
    try {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);
      Root<UserRole> root = cq.from(UserRole.class);
      cq.where(cb.equal(root.get("role"), role));
      Query q = entityManager.createQuery(cq);

      result = (UserRole) q.getSingleResult();

      return result;
    } catch (NoResultException e) {
      //stay silent, null will be returned
    }
    catch (PersistenceException e) {
      //throw new PersistenceFailure();
    }

    return result;
  }

}
