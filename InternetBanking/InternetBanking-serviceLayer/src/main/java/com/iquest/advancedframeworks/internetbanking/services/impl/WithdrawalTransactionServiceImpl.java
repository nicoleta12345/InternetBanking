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
import com.iquest.advancedframeworks.internetbanking.persistence.dao.WithdrawalTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.WithdrawalTransaction;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionAccounts;
import com.iquest.advancedframeworks.internetbanking.services.dto.WithdrawalTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

/**
 * The WithdrawalTransactionServiceImpl implements services for WithdrawalTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class WithdrawalTransactionServiceImpl implements WithdrawalTransactionService {

  /**
   * Logger instance used to log information from the WithdrawalTransactionServiceImpl class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawalTransactionServiceImpl.class);

  /**
   * The repository for the WithdrawalTransaction objects.
   */
  @Autowired
  WithdrawalTransactionDao withdrawalTransactionDao;

  /**
   * The repository for the Account objects.
   */
  @Autowired
  AccountDao accountDao;

  /**
   * The repository for the User objects.
   */
  @Autowired
  UserDao userDao;

  @Override
  @Transactional
  public TransactionAccounts getFormData(String currentUserUsername) {
    User currentUser = userDao.getUserByUsername(currentUserUsername);
    List<Account> userAccounts = currentUser.getAccounts();

    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<AccountDetailsDto>>() {}.getType();
    List<AccountDetailsDto> accountDetailsDto = modelMapper.map(userAccounts, listType);
    
    TransactionAccounts withdrawalFormDataDto = new TransactionAccounts();
    withdrawalFormDataDto.setUserAccounts(accountDetailsDto);

    return withdrawalFormDataDto;
  }

  @Override
  @Transactional
  public void addTransaction(WithdrawalTransactionDto withdrawalTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound {
    Account sender = accountDao.getAccountByNo(withdrawalTransaction.getSenderAccountNumber());
    validateSender(sender, currentUserUsername);

    sender.setAmount(sender.getAmount() - withdrawalTransaction.getValue());
    try {
      accountDao.update(sender);
    }
    catch (EntityDeletedException e) {
      throw new AccountNotFound("The sender account desn't exist!");
    }

    WithdrawalTransaction transaction = new WithdrawalTransaction();
    transaction.setSenderAccount(sender);
    transaction.setValue(withdrawalTransaction.getValue());
    try {
      withdrawalTransactionDao.create(transaction);
    }
    catch (EntityRegisteredException e) {
      // stay silent
    }

    LOGGER.info("new withdrawal transaction");
  }

  private void validateSender(Account sender, String username) throws AccountAccessDenied {
    User senderUser = userDao.getUserByAccount(sender);
    User currentUser = userDao.getUserByUsername(username);

    if (senderUser != currentUser) {
      throw new AccountAccessDenied("The current logged user is not the owner of the account!");
    }
  }

}
