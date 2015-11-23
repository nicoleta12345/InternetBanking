package com.iquest.advancedframeworks.internetbanking.services.impl;

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
   * An UserDao injected object used to make operations with the User objects into the database.
   */
  @Autowired
  private UserDao userDao;

  @Override
  @Transactional
  public User getUserbyId(Integer id) throws UserNotFound {
    User user = userDao.read(id);
    
    if (user == null) {
      throw new UserNotFound("The user could not be found into the database!");
    }

    return user;
  }

  @Override
  @Transactional
  public void insertUser(User user) {
    userDao.create(user);
  }

  @Override
  @Transactional
  public User getUserByAccount(Account sender) throws UserNotFound {
    User user = userDao.getUserByAccount(sender);

    if (user == null) {
      throw new UserNotFound("The user could not be found into the database!");
    }

    return user;
  }

  @Override
  @Transactional
  public User getUserByUsername(String username) throws UserNotFound {
    User user = userDao.getUserByUsername(username);

    if (user == null) {
      throw new UserNotFound("The user could not be found into the database!");
    }

    return user;
  }

}
