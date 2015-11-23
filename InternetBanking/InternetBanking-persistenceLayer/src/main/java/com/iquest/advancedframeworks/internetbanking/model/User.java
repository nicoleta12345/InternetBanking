package com.iquest.advancedframeworks.internetbanking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The User class represents a customer for an online banking application. Due
 * to @Entity annotation there will be generated a table for this class into a
 * database.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "user")
public class User {

  /**
   * The id of the user. It represents the primary key for the table which will
   * be generated.
   */
  @Id
  @GeneratedValue
  private int id;

  /**
   * The user code used for authentication.
   */
  @NotNull
  private String username;

  /**
   * The password used for authentication.
   */
  @NotNull
  private String password;

  /**
   * The user role for authentication.
   */
  @ManyToMany()
  @JoinTable(joinColumns = @JoinColumn(name = "role_Id"), inverseJoinColumns = @JoinColumn(name = "user_Id"))
  private Set<UserRole> roles;

  /**
   * The user details. Into the database the userdetails primary key(id) will be
   * set as a foreign key into the table generated for this class.
   */
  @OneToOne
  @JoinColumn(name = "userDetails_Id")
  private UserDetails userDetails;

  /**
   * The user accounts. Into the database the account primary key(id) will be
   * set as a foreign key into the table generated for this class.
   */
  @OneToMany()
  @JoinTable(joinColumns = @JoinColumn(name = "user_Id"), inverseJoinColumns = @JoinColumn(name = "account_Id"))
  private List<Account> accounts = new ArrayList<>();

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    result = prime * result
        + ((userDetails == null) ? 0 : userDetails.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (id != other.id)
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    if (userDetails == null) {
      if (other.userDetails != null)
        return false;
    } else if (!userDetails.equals(other.userDetails))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password="
        + password + ", userDetails=" + userDetails + ", accounts=" + accounts
        + "]";
  }

}
