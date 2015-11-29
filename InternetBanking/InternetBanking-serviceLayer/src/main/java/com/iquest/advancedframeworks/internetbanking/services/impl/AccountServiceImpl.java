package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountFormDataDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountRegisteredException;

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
  public void createAccount(AccountDetailsDto accountDetails) throws AccountRegisteredException {
    ModelMapper modelMapper = new ModelMapper();
    Account account = modelMapper.map(accountDetails, Account.class);

    try {
      accountDao.create(account);
    }
    catch (EntityRegisteredException e) {
      throw new AccountRegisteredException("The account already exists!");
    }

    LOGGER.info("AccountServiceImpl creates account: " + account);
  }

  @Override
  public AccountDetailsDto getAccountByNo(String accountNo) throws AccountNotFound {
    Account account = accountDao.getAccountByNo(accountNo);

    if (account == null) {
      LOGGER.error("AccountNotFound! Account Number: " + accountNo);
      throw new AccountNotFound("The account could not be found!");
    }

    ModelMapper modelMapper = new ModelMapper();
    AccountDetailsDto accountDetails = modelMapper.map(account, AccountDetailsDto.class);

    return accountDetails;
  }

  @Override
  @Transactional
  public AccountDetailsDto updateAccount(AccountDetailsDto accountDetails) throws AccountNotFound {
    Account updatedAccount = null;
    ModelMapper modelMapper = new ModelMapper();
    Account account = modelMapper.map(accountDetails, Account.class);

    try {
      updatedAccount = accountDao.update(account);
    }
    catch (EntityDeletedException e) {
      LOGGER.error("AccountNotFound The account doesn't exist!");
      throw new AccountNotFound("The account doesn't exist!");
    }

    LOGGER.info("Updated the account:" + account);

    AccountDetailsDto accountUpdatedDetails = modelMapper.map(updatedAccount, AccountDetailsDto.class);
    return accountUpdatedDetails;
  }

  @Override
  @Transactional
  public AccountDetailsDto getAccountDetails(String accountNumber, String currentUserUsername)
      throws AccountAccessDenied {
    Account account = accountDao.getAccountByNo(accountNumber);
    validateOwnerAccount(account, currentUserUsername);

    ModelMapper modelMapper = new ModelMapper();
    AccountDetailsDto accountDetails = modelMapper.map(account, AccountDetailsDto.class);

    return accountDetails;
  }

  /**
   * Checks if the current logged in user is the same with the owner of the passed Account object.
   * 
   * @param account the Account object used
   * @param currentUserUsername the username of the current user logged in
   * @throws AccountAccessDenied if the logged in user is not the same with the owner of the account
   */
  private void validateOwnerAccount(Account account, String currentUserUsername) throws AccountAccessDenied {
    User user = userDao.getUserByAccount(account);
    User currentUser = userDao.getUserByUsername(currentUserUsername);

    if (user != currentUser) {
      LOGGER.error("AccountAccessDenied The current logged user is not the owner of the account!");
      throw new AccountAccessDenied("The current logged user is not the owner of the account!");
    }
  }

  @Override
  @Transactional
  public AccountFormDataDto getFormData(String username) {
    User currentUser = userDao.getUserByUsername(username);

    List<Account> userAccounts = currentUser.getAccounts();

    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<AccountDetailsDto>>() {}.getType();
    List<AccountDetailsDto> accountDetailsDto = modelMapper.map(userAccounts, listType);
    
    AccountFormDataDto accountFormDataDto = new AccountFormDataDto();
    accountFormDataDto.setUserAccounts(accountDetailsDto);

    return accountFormDataDto;
  }

}
