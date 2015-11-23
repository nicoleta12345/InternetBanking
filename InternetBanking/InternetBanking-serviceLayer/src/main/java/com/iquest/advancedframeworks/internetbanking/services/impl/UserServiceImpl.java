package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

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
  public User getUserbyId(Integer id) {
    return userDao.read(id);
  }

  @Override
  @Transactional
  public void insertUser(User user) {

    userDao.create(user);
  }

  @Override
  @Transactional
  public User getUserByAccount(Account sender) {
    return userDao.getUserByAccount(sender);

  }

  @Override
  @Transactional
  public User getUserByUsername(String username) {
    return userDao.getUserByUsername(username);

  }

}
