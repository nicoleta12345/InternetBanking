package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

/**
 * The AccountServiceImpl class implements AccountService interface and calls an AccountDAo object to perform operations
 * with Accounts objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

  /**
   * An AccountDao injected object used to perform operations with Account objects.
   */
  @Autowired
  private AccountDao accountDao;

  /**
   * The repository for User objects
   */
  @Autowired
  private UserDao userDao;

  @Override
  @Transactional
  public void createAccount(Account account) {
    accountDao.create(account);
  }

  @Override
  public Account getAccountByNo(String accountNo) throws AccountNotFound {
    Account account = accountDao.getAccountByNo(accountNo);
    
    if(account == null) {
      throw new AccountNotFound("The account could not be found!");
    }
    
    return account;
  }

  @Override
  public Account updateAccount(Account account) {
    return accountDao.update(account);
  }

  @Override
  @Transactional
  public List<Account> getAccountsNo(User user) throws AccountNotFound {
    List<Account> accounts = userDao.getAccountsNo(user);
    
    if(accounts == null) {
      throw new AccountNotFound("The account could not be found!");
    }
    
    return accounts;
  }

}
