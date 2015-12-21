package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserRoleDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;
import com.iquest.advancedframeworks.internetbanking.services.UserRoleService;
import com.iquest.advancedframeworks.internetbanking.services.dto.UserRoleDto;
import com.iquest.advancedframeworks.services.exceptions.UserRoleRegisteredException;

/**
 * The UserRoleServiceImpl class implements services declared in the UserRoleService interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

  /**
   * Logger instance used to log information from the TransferTransactionServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);

  /**
   * An UserRoleDao injected object used to make operations with the UserRole objects into the database.
   */
  @Autowired
  UserRoleDao userRoleDao;

  @Override
  @Transactional
  public void addUserRole(UserRoleDto userRoleDto) throws UserRoleRegisteredException {
    ModelMapper modelMapper = new ModelMapper();
    UserRole userRole = modelMapper.map(userRoleDto, UserRole.class);
    
    try {
      userRoleDao.create(userRole);
    }
    catch (EntityRegisteredException e) {
      throw new UserRoleRegisteredException("The given UserRoleDetails already exist!");
    }
    
    LOGGER.info("new UserRole");
  }

}
