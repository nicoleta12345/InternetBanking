package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   * Logger instance used to log information from the AccountServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

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
    LOGGER.info("AccountServiceImpl creates account: " + account);
    accountDao.create(account);
  }

  @Override
  public Account getAccountByNo(String accountNo) throws AccountNotFound {
    Account account = accountDao.getAccountByNo(accountNo);

    if (account == null) {
      LOGGER.error("AccountNotFound! Account Number: " + accountNo);
      throw new AccountNotFound("The account could not be found!");
    }

    return account;
  }

  @Override
  public Account updateAccount(Account account) {
    LOGGER.info("Updates the account:" + account);
    return accountDao.update(account);
  }

  @Override
  @Transactional
  public List<Account> getAccountsNo(User user) throws AccountNotFound {
    List<Account> accounts = userDao.getAccountsNo(user);

    if (accounts == null) {
      LOGGER.error("AccountNotFound! The accountd of the user " + user + "could not be found");
      throw new AccountNotFound("The account could not be found!");
    }

    return accounts;
  }

}
