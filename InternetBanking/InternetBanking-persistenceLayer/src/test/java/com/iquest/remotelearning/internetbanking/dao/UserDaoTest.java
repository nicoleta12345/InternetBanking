package com.iquest.remotelearning.internetbanking.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.configuration.PersistenceConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfiguration.class, loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(defaultRollback = false)
@Sql("classpath:initialData.sql")
public class UserDaoTest {
  @Test
  @Transactional
public void createUser() {}
 
//  @Autowired
//  UserService userService;
//
//  @Autowired
//  AccountService accountService;
//
//  @Test
//  @Transactional
//  public void createUser() {
//    User user = userService.getUserbyId(1);
//    if (user == null) {
//      user = new User();
//      Account account = new Account();
//      UserDetails userDetails = new UserDetails();
//      Address address = new Address();
//      List<Account> accounts = new ArrayList<>();
//      
//      UserRole userRole = new UserRole();
//      userRole.setRole("ROLE_USER");
//     
//      Set<UserRole> roles = new HashSet<>();
//      roles.add(userRole);
//
//      account.setAccountNumber("123");
//      account.setAmount(100.5);
//      accounts.add(account);
//
//      address.setStreetName("Strada Merilor");
//      address.setStreetNumber(21);
//      address.setTown("Craiova");
//      address.setPostalCode(Long.valueOf(123));
//
//      userDetails.setFirstName("Ana");
//      userDetails.setLastName("Popescu");
//      userDetails.setCnp("12345678901234");
//      userDetails.setAge(22);
//      userDetails.setEmail("lala@yahoo.com");
//      userDetails.setAddress(address);
//
//      user.setPassword("user");
//      user.setUsername("user");
//      user.setRoles(roles);
//      user.setAccounts(accounts);
//      user.setUserDetails(userDetails);
//
//      // creates an account
//      accountService.createAccount(account);
//      // inserts a user into a database
//      userService.insertUser(user);
//    }
//  }
//
//  @Test
//  @Transactional
//  public void createAdmin() {
//    User user = userService.getUserbyId(1);
//    if (user == null) {
//      user = new User();
//      Account account = new Account();
//      UserDetails userDetails = new UserDetails();
//      Address address = new Address();
//      List<Account> accounts = new ArrayList<>();
//      
//      UserRole userRole = new UserRole();
//      userRole.setRole("ROLE_ADMIN");
//     
//      Set<UserRole> roles = new HashSet<>();
//      roles.add(userRole);
//
//
//      account.setAccountNumber("150");
//      account.setAmount(100.5);
//      accounts.add(account);
//
//      address.setStreetName("Strada Merilor");
//      address.setStreetNumber(21);
//      address.setTown("Craiova");
//      address.setPostalCode(Long.valueOf(123));
//
//      userDetails.setFirstName("Ana");
//      userDetails.setLastName("Popescu");
//      userDetails.setCnp("12345678901234");
//      userDetails.setAge(22);
//      userDetails.setEmail("lala@yahoo.com");
//      userDetails.setAddress(address);
//
//      user.setPassword("admin");
//      user.setUsername("admin");
//      user.setRoles(roles);
//      user.setAccounts(accounts);
//      user.setUserDetails(userDetails);
//
//      // creates an account
//      accountService.createAccount(account);
//      // inserts a user into a database
//      userService.insertUser(user);
//    }
//  }

}
