package com.iquest.advancedframeworks.internetbanking.persistence.dao;

import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserRoleDao interface adds operations which can be performed with UserRole entities besides the CRUD operations
 * inherited from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserRoleDao extends GenericDao<UserRole> {

  /**
   * Gets a UserRole object identified by its role.
   * 
   * @param role the role
   * @return a UserRole object
   */
  UserRole getUserRolebyRole(String role);
  
}
