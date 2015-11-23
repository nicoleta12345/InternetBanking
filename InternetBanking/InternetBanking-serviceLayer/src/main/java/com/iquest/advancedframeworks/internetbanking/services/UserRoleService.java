package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserRoleService defines services for UserRole objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserRoleService {

  /**
   * Adds a new UserRole object.
   * 
   * @param userRole the UserRole object
   */
  void addUserRole(UserRole userRole);

}
