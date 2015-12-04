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
import com.iquest.advancedframeworks.internetbanking.persistence.model.Transfer;
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
   * The repository for the Transaction objects.
   */
  @Autowired
  TransactionDao transactionDao;

  /**
   * The repository for the User entities.
   */
  @Autowired
  UserDao userDao;

  /**
   * The repository for the Client entities.
   */
  @Autowired
  ClientDao clientDao;
  
  /**
   * The repository for the Account entities.
   */
  @Autowired
  AccountDao accountDao;

  @Override
  @Transactional
  public TransactionAccounts getFormData(String username) {
    Client currentUser = (Client) userDao.getUserByUsername(username);
    List<Account> userAccounts = currentUser.getAccounts();

    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<AccountDetailsDto>>() {
    }.getType();
    List<AccountDetailsDto> accountDetailsDto = modelMapper.map(userAccounts, listType);

    TransactionAccounts transactionAccounts = new TransactionAccounts();
    transactionAccounts.setUserAccounts(accountDetailsDto);

    return transactionAccounts;
  }

  @Override
  @Transactional
  public void addTransaction(TransferTransactionDto transferTransaction, String currentUserUsername)
      throws AccountAccessDenied, AccountNotFound {
    Account sender = accountDao.getAccountByNo(transferTransaction.getSenderAccountNumber());

    validateSender(sender, currentUserUsername);
    updateSenderDetails(transferTransaction, sender);

    Account receiver = accountDao.getAccountByNo(transferTransaction.getReceiverAccountNumber());
    updateReceiverDetails(transferTransaction, sender, receiver);

    Transfer transaction = new Transfer();
    transaction.setSenderAccount(sender);
    transaction.setReceiverAccount(receiver);
    transaction.setValue(transferTransaction.getValue());
    if (receiver == null) {
      transaction.setPending(true);
    }

    try {
      transactionDao.create(transaction);
    } catch (EntityRegisteredException e) {
      // stay silent
    }
    LOGGER.info("New transfer transaction");
  }

  /**
   * Updates the sender account. There will be taken from the sender account the amount transfered.
   * 
   * @param transferTransaction the transfer details
   * @param sender the sender account involved in transaction
   * @throws AccountNotFound thrown if the account could not be found
   */
  private void updateSenderDetails(TransferTransactionDto transferTransaction, Account sender) throws AccountNotFound {
    sender.setAmount(sender.getAmount() - transferTransaction.getValue());
    
    try {
      accountDao.update(sender);
    } catch (EntityDeletedException e1) {
      throw new AccountNotFound("The account doesn't exist!");
    }
  }

  /**
   * Updates the receiver account details. If it can be found into the persistence of the application, its amount is
   * increased with the value of the transfer, otherwise the value of the transfer becomes blocked amount for the sender
   * account.
   * 
   * @param transferTransaction the details of the transaction
   * @param sender the sender account
   * @param receiver the receiver account
   */
  private void updateReceiverDetails(TransferTransactionDto transferTransaction, Account sender, Account receiver) {
    if (receiver == null) {
      double senderBlockedAmount = sender.getBlockedAmount();
      sender.setBlockedAmount(senderBlockedAmount + transferTransaction.getValue());
    } else {
      receiver.setAmount(receiver.getAmount() + transferTransaction.getValue());
      
      try {
        accountDao.update(receiver);
      } catch (EntityDeletedException e) {
        // stay silent
      }
      
    }
  }

  /**
   * Checks if the owner of the sender account is the same with the current logged in user.
   * 
   * @param sender the sender account involved in the transaction
   * @param username the username of the current user logged in
   * @throws AccountAccessDenied thrown if the current logged in user is not the owner of the sender account
   */
  private void validateSender(Account sender, String username) throws AccountAccessDenied {
    Client senderClient = clientDao.getClientByAccount(sender);
    Client currentClient = (Client) userDao.getUserByUsername(username);

    if (senderClient != currentClient) {
      throw new AccountAccessDenied("The current logged user is not the owner of the account!");
    }

  }

}
