package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.dao.UserRoleDao;
import com.iquest.advancedframeworks.internetbanking.model.UserRole;
import com.iquest.advancedframeworks.internetbanking.services.UserRoleService;

/**
 * The UserRoleServiceImpl class implements services declared in the UserRoleService interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

  /**
   * An UserRoleDao injected object used to make operations with the UserRole objects into the database.
   */
  @Autowired
  UserRoleDao userRoleDao;

  @Override
  @Transactional
  public void addUserRole(UserRole userRole) {
    userRoleDao.create(userRole);

  }

}
