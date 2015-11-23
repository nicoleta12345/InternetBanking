package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Address;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserDetails;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.UserRoleService;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

@Controller
public class HomeController {

  @Autowired
  UserService userService;

  @Autowired
  AccountService accountService;

  @Autowired
  UserRoleService userRoleService;

  @RequestMapping(value = "/loadTestData", method = RequestMethod.GET)
  public String loadTestData() {
    createUser();
    createAdmin();
    
    return "login";
  }

  private void createUser() {
    User user = new User();
    Account account = new Account();
    UserDetails userDetails = new UserDetails();
    Address address = new Address();
    List<Account> accounts = new ArrayList<>();

    UserRole userRole = new UserRole();
    userRole.setRole("ROLE_USER");
    userRoleService.addUserRole(userRole);

    Set<UserRole> roles = new HashSet<>();
    roles.add(userRole);

    account.setAccountNumber("123");
    account.setAmount(100.5);
    accounts.add(account);

    address.setStreetName("Strada Merilor");
    address.setStreetNumber(21);
    address.setTown("Craiova");
    address.setPostalCode(Long.valueOf(123));

    userDetails.setFirstName("Ana");
    userDetails.setLastName("Popescu");
    userDetails.setCnp("12345678901234");
    userDetails.setAge(22);
    userDetails.setEmail("lala@yahoo.com");
    userDetails.setAddress(address);

    user.setPassword("user");
    user.setUsername("user");
    user.setRoles(roles);
    user.setAccounts(accounts);
    user.setUserDetails(userDetails);

    // creates an account
    accountService.createAccount(account);
    // inserts a user into a database
    userService.insertUser(user);

  }

  private void createAdmin() {
    User user = new User();
    user = new User();
    Account account = new Account();
    UserDetails userDetails = new UserDetails();
    Address address = new Address();
    List<Account> accounts = new ArrayList<>();

    UserRole userRole = new UserRole();
    userRole.setRole("ROLE_ADMIN");
    userRoleService.addUserRole(userRole);

    Set<UserRole> roles = new HashSet<>();
    roles.add(userRole);

    account.setAccountNumber("150");
    account.setAmount(100.5);
    accounts.add(account);

    address.setStreetName("Strada Merilor");
    address.setStreetNumber(21);
    address.setTown("Craiova");
    address.setPostalCode(Long.valueOf(123));

    userDetails.setFirstName("Ana");
    userDetails.setLastName("Popescu");
    userDetails.setCnp("12345678901234");
    userDetails.setAge(22);
    userDetails.setEmail("lala@yahoo.com");
    userDetails.setAddress(address);

    user.setPassword("admin");
    user.setUsername("admin");
    user.setRoles(roles);
    user.setAccounts(accounts);
    user.setUserDetails(userDetails);

    // creates an account
    accountService.createAccount(account);
    // inserts a user into a database
    userService.insertUser(user);

  }

}
