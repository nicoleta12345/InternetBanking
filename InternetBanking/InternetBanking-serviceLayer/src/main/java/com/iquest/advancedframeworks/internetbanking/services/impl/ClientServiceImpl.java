package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.ClientDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.ClientService;
import com.iquest.advancedframeworks.internetbanking.services.dto.AccountDetailsDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.ClientDto;
import com.iquest.advancedframeworks.services.exceptions.UserNotFound;
import com.iquest.advancedframeworks.services.exceptions.UserRegisteredException;

/**
 * The UserServiceImpl class represents a service which use a UserDao injected object to perform operations with User
 * objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

  /**
   * Logger instance used to log information from the UserServiceImpl class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

  /**
   * The repository for the User objects.
   */
  @Autowired
  private UserDao userDao;

  /**
   * The repository for the Client objects.
   */
  @Autowired
  ClientDao clientDao;

  @Override
  @Transactional
  public ClientDto getUserbyId(Integer id) throws UserNotFound {
    User user = userDao.read(id);

    if (user == null) {
      LOGGER.error("UserNotFound! The client with the id " + id + "could not be found!");
      throw new UserNotFound("The client could not be found into the database!");
    }

    LOGGER.debug("gets the client: " + user + "by id");
    ModelMapper modelMapper = new ModelMapper();
    ClientDto userDto = modelMapper.map(user, ClientDto.class);

    return userDto;
  }

  @Override
  @Transactional
  public void insertUser(ClientDto userDto) throws UserRegisteredException {
    ModelMapper modelMapper = new ModelMapper();
    Client client = modelMapper.map(userDto, Client.class);

    try {
      userDao.create(client);
    } catch (EntityRegisteredException e) {
      throw new UserRegisteredException("The client already exists!");
    }
    LOGGER.debug("inserted new Client: " + client);
  }

  @Override
  @Transactional
  public ClientDto getUserByAccount(AccountDetailsDto sender) throws UserNotFound {
    ModelMapper modelMapper = new ModelMapper();
    Account senderAccount = modelMapper.map(sender, Account.class);

    User user = clientDao.getClientByAccount(senderAccount);
    if (user == null) {
      LOGGER.error("UserNotFound! The client identified by the Account" + sender + "could not be found!");
      throw new UserNotFound("The client could not be found into the database!");
    }

    LOGGER.debug("gets a client by account, user info: " + sender);
    ClientDto userDto = modelMapper.map(user, ClientDto.class);

    return userDto;
  }

  @Override
  @Transactional
  public ClientDto getUserByUsername(String username) throws UserNotFound {
    Client client = (Client) userDao.getUserByUsername(username);

    if (client == null) {
      LOGGER.error("UserNotFound! The client with the username " + username + "could not be found!");
      throw new UserNotFound("The client could not be found into the database!");
    }

    LOGGER.debug("gets a client by username, the user returned: " + client);
    ModelMapper modelMapper = new ModelMapper();
    ClientDto clientDto = modelMapper.map(client, ClientDto.class);

    return clientDto;
  }

}
