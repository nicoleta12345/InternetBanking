package com.iquest.advancedframeworks.internetbanking.integration.externalRest.quartz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.integration.externalRest.model.ExternalUser;
import com.iquest.advancedframeworks.internetbanking.integration.externalRest.model.ExternalUsers;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserRoleDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityRegisteredException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Role;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The ExternalUsersIntegration class persists users received from an external source.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class ExternalUsersIntegration {

  /**
   * The logger for the ExternalUsersIntegration class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ExternalUsersIntegration.class);

  /**
   * The User repository.
   */
  @Autowired
  private UserDao userDao;

  /**
   * The UserRole repository
   */
  @Autowired
  private UserRoleDao userRoleDao;

  /**
   * Persist externals users as regular clients.
   * 
   * @param externalUsers the object which contains details about the external users
   */
  @Transactional
  public void persistUsers(ExternalUsers externalUsers) {
    List<ExternalUser> users = externalUsers.getUsers();

    for (ExternalUser externalUser : users) {
      Client user = createNewClient(externalUser);

      try {
        User oldUser = userDao.getUserByCnp(user.getCnp());

        if (oldUser == null) {
          System.out.println(user);
          userDao.create(user);
        }
      }
      catch (EntityRegisteredException e) {
        LOGGER.error(e.getClass().getSimpleName(), e.getMessage());
      }
    }

  }

  /**
   * Creates a new Client object with the details of the external user.
   * 
   * @param externalUser the details about the external user
   * @return a new Client object
   */
  private Client createNewClient(ExternalUser externalUser) {
    Client user = new Client();

    Set<UserRole> clientRoles = new HashSet<>();
    Role role = Role.ROLE_USER;
    UserRole clientRole = userRoleDao.getUserRolebyRole(role);
    clientRoles.add(clientRole);

    user.setCnp(externalUser.getCnp() + "00000000");
    user.setFirstName(externalUser.getFirstName());
    user.setLastName(externalUser.getLastName());
    user.setUsername(externalUser.getFirstName() + externalUser.getId());
    user.setPassword(externalUser.getPassword());
    user.setAge(20);
    user.setRoles(clientRoles);

    return user;
  }

}
