package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserRoleDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;
import com.iquest.advancedframeworks.internetbanking.persistence.model.CreditAccount;
import com.iquest.advancedframeworks.internetbanking.persistence.model.SavingsAccount;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;
import com.iquest.advancedframeworks.internetbanking.services.AdminService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.UserDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountRegisteredException;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserRegisteredException;

@Service
public class AdminServiceImpl implements AdminService {

  /**
   * Logger instance used to log information from the AccountServiceImpl.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

  /**
   * The repository for the User objects.
   */
  @Autowired
  private UserDao userDao;

  /**
   * The repository for the Account objects.
   */
  @Autowired
  private AccountDao accountDao;

  /**
   * The repository for the UserRole objects.
   */
  @Autowired
  private UserRoleDao userRoleDao;

  @Autowired
  private MailSenderService mailSender;

  @Override
  @Transactional
  public void registerNewClient(UserDto userDto) throws UserRegisteredException {
    ModelMapper modelMapper = new ModelMapper();
    Client user = modelMapper.map(userDto, Client.class);
    setUserRole(user);

    try {
      userDao.create(user);
    }
    catch (EntityRegisteredException e) {
      LOGGER.error("UserRegisteredException The user already exists!");
      throw new UserRegisteredException("The user already exists!");
    }

    sendRegistrationMail(user);
  }

  /**
   * Sets the role of a user.
   * 
   * @param user the client details
   */
  private void setUserRole(Client user) {
    String role = "ROLE_USER";
    UserRole clientRole = userRoleDao.getUserRolebyRole(role);

    if (clientRole == null) {
      clientRole = new UserRole();
      clientRole.setRole(role);
      try {
        userRoleDao.create(clientRole);
      }
      catch (EntityRegisteredException e) {
        // stay silent
      }

    }

    Set<UserRole> clientRoles = new HashSet<>();
    clientRoles.add(clientRole);
    user.setRoles(clientRoles);
  }

  @Override
  @Transactional
  public void registerNewAccount(AccountDetailsDto accountDetails, String accountType, String cnpClient)
      throws AccountRegisteredException {
    Account account = createAccount(accountDetails, accountType);

    Client client = (Client) userDao.getUserByCnp(cnpClient);
    List<Account> accounts = client.getAccounts();
    accounts.add(account);

    try {
      userDao.update(client);
    }
    catch (EntityDeletedException e1) {
      // stay silent
    }

  }

  /**
   * Sends a confirmation mail to the client with his username and password.
   * 
   * @param user the new registered user
   */
  private void sendRegistrationMail(Client user) {
    String subject = "Registration confirmation";
    StringBuilder msg = new StringBuilder();
    String separator = " ";

    msg.append("Dear ");
    msg.append(user.getFirstName());
    msg.append(separator);
    msg.append("thank you for registrating.");
    msg.append("Your account details are: username: ");
    msg.append(user.getUsername());
    msg.append(" password: ");
    msg.append(user.getPassword());

    mailSender.sendMail(user.getEmail(), subject, msg.toString());
  }

  /**
   * Creates a new account with the details received as a parameter.
   * 
   * @param accountDetails the details about the account which will be created
   * @return the new account
   * @throws AccountRegisteredException if the account is already registered
   */
  private Account createAccount(AccountDetailsDto accountDetails, String accountType)
      throws AccountRegisteredException {
    ModelMapper modelMapper = new ModelMapper();
    Account account = null;

    if (accountType.equals("Savings Account")) {
      account = modelMapper.map(accountDetails, SavingsAccount.class);
    }
    else if (accountType.equals("Credit Account")) {
      account = modelMapper.map(accountDetails, CreditAccount.class);
    }

    try {
      accountDao.create(account);
    }
    catch (EntityRegisteredException e) {
      LOGGER.error("AccountRegisteredException The account already exists!");
      throw new AccountRegisteredException("The account already exists!");
    }

    return account;
  }

}
