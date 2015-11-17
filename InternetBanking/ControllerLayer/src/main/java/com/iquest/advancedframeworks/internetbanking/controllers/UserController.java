package com.iquest.advancedframeworks.internetbanking.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.Address;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserDetails;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

/**
 * The UserController class represents a controller which interacts with the
 * views and the service implementations. It is mapped to a certain url.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

  /**
   * An injected UserService object used to perform operations with User
   * objects.
   */
  @Autowired
  UserService userService;

  /**
   * An injected AccountService object used to perform operations with Account
   * objects.
   */
  @Autowired
  AccountService accountService;

  @RequestMapping("/login")
  public void login(HttpSession session) {
    /**/
    User user = userService.getUserbyId(1);
    if (user == null) {
      user = new User();
      Account account = new Account();
      UserDetails userDetails = new UserDetails();
      Address address = new Address();
      List<Account> accounts = new ArrayList<>();

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

      user.setPassword("pass");
      user.setUserCode("456");
      user.setAccounts(accounts);
      user.setUserDetails(userDetails);

      // creates an account
      accountService.createAccount(account);
      // inserts a user into a database
      userService.insertUser(user, userDetails, address);
    }

    session.setAttribute("user", user);
  }

}
