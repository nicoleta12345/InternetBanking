package com.iquest.advancedframeworks.internetbanking.integration.externalRest.quartz;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@Service
public class ExternalUsersIntegration {

 @Autowired
  private UserDao userDao;
 
 @Autowired
 private UserRoleDao userRoleDao;

  @Transactional
  public void persistUsers(ExternalUsers externalUsers) {
    Client user;
    List<ExternalUser> users = externalUsers.getUsers();

    Set<UserRole> clientRoles = new HashSet<>();
    Role role = Role.ROLE_USER;
    UserRole clientRole = userRoleDao.getUserRolebyRole(role);
    clientRoles.add(clientRole);

    for (ExternalUser externalUser : users) {
      user = new Client();

      user.setCnp(externalUser.getCnp() + "00000000");
      user.setFirstName(externalUser.getFirstName());
      user.setLastName(externalUser.getLastName());
      user.setUsername(externalUser.getFirstName() + externalUser.getId());
      user.setPassword(externalUser.getPassword());
      user.setAge(20);
      user.setRoles(clientRoles);

      try {
        User oldUser = userDao.getUserByCnp(user.getCnp());

        if (oldUser == null) {
          System.out.println(user);
          userDao.create(user);
        }
      }
      catch (EntityRegisteredException e) {
        e.printStackTrace();
      }
    }

  }
}
