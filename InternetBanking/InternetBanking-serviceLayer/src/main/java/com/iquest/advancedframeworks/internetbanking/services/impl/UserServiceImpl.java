package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.UserService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.UserDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserRegisteredException;

/**
 * The UserServiceImpl class represents a service which use a UserDao injected object to perform operations with User
 * objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * Logger instance used to log information from the UserServiceImpl class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  /**
   * The repository for the User objects.
   */
  @Autowired
  private UserDao userDao;

  @Override
  @Transactional
  public UserDto getUserbyId(Integer id) throws UserNotFound {
    User user = userDao.read(id);

    if (user == null) {
      LOGGER.error("UserNotFound! The user with the id " + id + "could not be found!");
      throw new UserNotFound("The user could not be found into the database!");
    }

    LOGGER.debug("gets the user: " + user + "by id");
    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(user, UserDto.class);
    
    return userDto;
  }

  @Override
  @Transactional
  public void insertUser(UserDto userDto) throws UserRegisteredException {
    ModelMapper modelMapper = new ModelMapper();
    User user = modelMapper.map(userDto, User.class);
   
    try {
      userDao.create(user);
    }
    catch (EntityRegisteredException e) {
      throw new UserRegisteredException("The user already exists!");
    }
    LOGGER.debug("inserted new User: " + user);
  }

  @Override
  @Transactional
  public UserDto getUserByAccount(AccountDetailsDto sender) throws UserNotFound {
    ModelMapper modelMapper = new ModelMapper();
    Account senderAccount = modelMapper.map(sender, Account.class);
    
    User user = userDao.getUserByAccount(senderAccount);
    if (user == null) {
      LOGGER.error("UserNotFound! The user identified by the Account" + sender + "could not be found!");
      throw new UserNotFound("The user could not be found into the database!");
    }

    LOGGER.debug("gets a user by account, user info: " + sender);
    UserDto userDto = modelMapper.map(user, UserDto.class);
    
    return userDto;
  }

  @Override
  @Transactional
  public UserDto getUserByUsername(String username) throws UserNotFound {
    User user = userDao.getUserByUsername(username);

    if (user == null) {
      LOGGER.error("UserNotFound! The user with the username " + username + "could not be found!");
      throw new UserNotFound("The user could not be found into the database!");
    }

    LOGGER.debug("gets a user by username, the user returned: " + user);
    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(user, UserDto.class);
    
    return userDto;
  }

}
