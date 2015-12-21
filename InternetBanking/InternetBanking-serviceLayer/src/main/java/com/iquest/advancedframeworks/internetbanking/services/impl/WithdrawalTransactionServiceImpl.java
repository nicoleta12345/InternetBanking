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
import com.iquest.advancedframeworks.internetbanking.persistence.dao.ClientDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Withdrawal;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionAccounts;
import com.iquest.advancedframeworks.internetbanking.services.dto.WithdrawalTransactionDto;
import com.iquest.advancedframeworks.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.services.exceptions.AccountNotFound;

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
   * The repository for the Transaction objects.
   */
  @Autowired
  TransactionDao transactionDao;

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

  /**
   * The repository of the Client objects.
   */
  @Autowired
  ClientDao clientDao;

  @Override
  @Transactional
  public TransactionAccounts getFormData(String currentUserUsername) {
    Client currentUser = (Client) userDao.getUserByUsername(currentUserUsername);
    List<Account> userAccounts = currentUser.getAccounts();

    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<AccountDetailsDto>>() {
    }.getType();
    List<AccountDetailsDto> accountDetailsDto = modelMapper.map(userAccounts, listType);

    TransactionAccounts withdrawalFormDataDto = new TransactionAccounts();
    withdrawalFormDataDto.setUserAccounts(accountDetailsDto);

    return withdrawalFormDataDto;
  }

  @Override
  @Transactional
  public void addTransaction(WithdrawalTransactionDto withdrawalTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound {
    Account sender = updateSenderAccount(withdrawalTransaction, currentUserUsername);
    
    System.out.println("sender:   " + sender);

    Withdrawal transaction = new Withdrawal();
    transaction.setSenderAccount(sender);
    transaction.setValue(withdrawalTransaction.getValue());
    try {
      transactionDao.create(transaction);
    } catch (EntityRegisteredException e) {
      // stay silent
    }

    LOGGER.info("new withdrawal transaction");
  }

  /**
   * Updates the sender account details.
   * 
   * @param withdrawalTransaction the details about the withdrawal
   * @param currentUserUsername the username of the current logged in user
   * @return the updated account
   * @throws AccountAccessDenied if the logged in user is not the owner of the account
   * @throws AccountNotFound if the account is not found
   */
  @Transactional
  private Account updateSenderAccount(WithdrawalTransactionDto withdrawalTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound {
    Account sender = accountDao.getAccountByNo(withdrawalTransaction.getSenderAccountNumber());
    validateSender(sender, currentUserUsername);

    sender.setAmount(sender.getAmount() - withdrawalTransaction.getValue());
    try {
      accountDao.update(sender);
    } catch (EntityDeletedException e) {
      throw new AccountNotFound("The sender account desn't exist!");
    }
    
    return sender;
  }

  /**
   * Checks if the owner of the sender account is the same with the current logged in user.
   * 
   * @param sender the sender account
   * @param username the username of the current logged in user
   * @throws AccountAccessDenied if the current logged in user is not the owner of the sender account
   */
  private void validateSender(Account sender, String username) throws AccountAccessDenied {
    Client senderClient = clientDao.getClientByAccount(sender);
    Client currentClient = (Client) userDao.getUserByUsername(username);

    if (senderClient != currentClient) {
      throw new AccountAccessDenied("The current logged user is not the owner of the account!");
    }
  }

  @Override
  public void addTransaction(WithdrawalTransactionDto withdrawalDto, Integer clientId) throws AccountAccessDenied, AccountNotFound {
    User currentUser = userDao.read(clientId);
    String username = currentUser.getUsername();
    
    addTransaction(withdrawalDto, username);    
  }

}
