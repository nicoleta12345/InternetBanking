package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.UserService;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserNotFound;

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
   * Logger instance used to log information from the UserServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  /**
   * An UserDao injected object used to make operations with the User objects into the database.
   */
  @Autowired
  private UserDao userDao;

  @Override
  @Transactional
  public User getUserbyId(Integer id) throws UserNotFound {
    User user = userDao.read(id);

    if (user == null) {
      LOGGER.error("UserNotFound! The user with the id " + id + "could not be found!");
      throw new UserNotFound("The user could not be found into the database!");
    }

    return user;
  }

  @Override
  @Transactional
  public void insertUser(User user) {
    LOGGER.info("new User");
    userDao.create(user);
  }

  @Override
  @Transactional
  public User getUserByAccount(Account sender) throws UserNotFound {
    User user = userDao.getUserByAccount(sender);

    if (user == null) {
      LOGGER.error("UserNotFound! The user identified by the Account" + sender + "could not be found!");
      throw new UserNotFound("The user could not be found into the database!");
    }

    return user;
  }

  @Override
  @Transactional
  public User getUserByUsername(String username) throws UserNotFound {
    User user = userDao.getUserByUsername(username);

    if (user == null) {
      LOGGER.error("UserNotFound! The user with the username " + username + "could not be found!");
      throw new UserNotFound("The user could not be found into the database!");
    }

    return user;
  }

}
