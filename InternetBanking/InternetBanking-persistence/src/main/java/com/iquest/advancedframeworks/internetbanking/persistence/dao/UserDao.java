package com.iquest.advancedframeworks.internetbanking.persistence.dao;

import com.iquest.advancedframeworks.internetbanking.persistence.model.User;

/**
 * The UserDao interface adds operations which can be performed with User entities besides the CRUD operations inherited
 * from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserDao extends GenericDao<User> {

  /**
   * Gets a User object identified by its username.
   * 
   * @param username the username of the user
   * @return a User object
   */
  User getUserByUsername(String username);

  /**
   * Gets a User object identified by its cnp.
   * 
   * @param cnp the cnp of the user
   * @return a User object
   */
  User getUserByCnp(String cnp);

}
