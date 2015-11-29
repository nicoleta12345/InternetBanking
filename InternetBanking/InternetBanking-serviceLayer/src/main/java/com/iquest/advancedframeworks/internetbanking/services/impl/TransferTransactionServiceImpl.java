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
import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransferTransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.TransferTransaction;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionAccounts;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

/**
 * The TransferTransactionServiceImpl offers services which interacts with TransferTransaction objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class TransferTransactionServiceImpl implements TransferTransactionService {

  /**
   * Logger instance used to log information from the TransferTransactionServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(TransferTransactionServiceImpl.class);

  /**
   * The repository for the TransferTransaction entities.
   */
  @Autowired
  TransferTransactionDao transferTransactionDao;

  /**
   * The repository for the User entities.
   */
  @Autowired
  UserDao userDao;

  /**
   * The repository for the Account entities.
   */
  @Autowired
  AccountDao accountDao;

  @Override
  @Transactional
  public  TransactionAccounts getFormData(String username) {
    User currentUser = userDao.getUserByUsername(username);
    List<Account> userAccounts = currentUser.getAccounts();
    
    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<AccountDetailsDto>>() {}.getType();
    List<AccountDetailsDto> accountDetailsDto = modelMapper.map(userAccounts, listType);
    
    TransactionAccounts transactionAccounts = new TransactionAccounts();
    transactionAccounts.setUserAccounts(accountDetailsDto);
    System.out.println(transactionAccounts);

    return transactionAccounts;
  }

  @Override
  @Transactional
  public void addTransaction(TransferTransactionDto transferTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound {
    Account sender = accountDao.getAccountByNo(transferTransaction.getSenderAccountNumber());
    validateSender(sender, currentUserUsername);

    Account receiver = accountDao.getAccountByNo(transferTransaction.getReceiverAccountNumber());

    sender.setAmount(sender.getAmount() - transferTransaction.getValue());
    try {
      accountDao.update(sender);
    } catch (EntityDeletedException e1) {
      throw new AccountNotFound("The account doesn't exist!");
    }

    receiver.setAmount(receiver.getAmount() + transferTransaction.getValue());
    try {
      accountDao.update(receiver);
    } catch (EntityDeletedException e) {
      throw new AccountNotFound("The account doesn't exist!");
    }

    TransferTransaction transaction = new TransferTransaction();
    transaction.setSenderAccount(sender);
    transaction.setReceiverAccount(receiver);
    transaction.setValue(transferTransaction.getValue());

    LOGGER.info("New transfer transaction");
    try {
      transferTransactionDao.create(transaction);
    }
    catch (EntityRegisteredException e) {
      // stay silent
    }
  }

  private void validateSender(Account sender, String username) throws AccountAccessDenied {
    User senderUser = userDao.getUserByAccount(sender);
    User currentUser = userDao.getUserByUsername(username);

    if (senderUser != currentUser) {
      throw new AccountAccessDenied("The current logged user is not the owner of the account!");
    }

  }

}
