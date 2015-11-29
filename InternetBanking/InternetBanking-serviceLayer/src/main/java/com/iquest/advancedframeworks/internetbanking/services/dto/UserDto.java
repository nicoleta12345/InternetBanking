package com.iquest.advancedframeworks.internetbanking.services.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserDetails;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserDto class represents a DTO which contains details about a user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class UserDto {

  /**
   * The username of the user
   */
  private String username;

  /**
   * The password of the user.
   */
  private String password;

  /**
   * The user role for authentication.
   */
  private Set<UserRole> roles;

  /**
   * The user details. Into the database the userdetails primary key(id) will be set as a foreign key into the table
   * generated for this class.
   */
  private UserDetails userDetails;

  /**
   * The user accounts. Into the database the account primary key(id) will be set as a foreign key into the table
   * generated for this class.
   */
  private List<Account> accounts = new ArrayList<>();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetails userDetails) {
    this.userDetails = userDetails;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
