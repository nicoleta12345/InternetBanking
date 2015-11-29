package com.iquest.advancedframeworks.internetbanking.services;

import com.iquest.advancedframeworks.internetbanking.services.dto.UserRoleDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserRoleRegisteredException;

/**
 * The UserRoleService defines services for UserRole objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface UserRoleService {

  /**
   * Adds a new UserRole object, constructed with the information from a UserRoleDto.
   * 
   * @param userRoleDto the UserRoleDto object
   * @throws UserRoleRegisteredException thrown if the UserRole already exists
   */
  void addUserRole(UserRoleDto userRole) throws UserRoleRegisteredException;

}
