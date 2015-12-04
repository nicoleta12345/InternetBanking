package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 * The Client class represents a more specific type of user. It represents the client of a bank.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("Client")
public class Client extends User {

  /**
   * The client accounts. Into the database the account primary key(id) will be set as a foreign key into the table
   * generated for this class.
   */
  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID") )
  private List<Account> accounts = new ArrayList<>();

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

}
