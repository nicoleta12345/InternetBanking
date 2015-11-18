package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

/**
 * The UserServiceImpl class represents a service which use a UserDao injected
 * object to perform operations with User objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * An UserDao injected object used to make operations with the User objects
   * into the database.
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
  public User getUserByAccount(Account sender) {
    return userDao.getUserByAccount(sender);

  }

  @Override
  public List<Account> getAccountsNo(User user) {
    return userDao.getAccountsNo(user);
  }

}
