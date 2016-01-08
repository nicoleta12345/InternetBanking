package com.iquest.advancedframeworks.internetbanking.persistence.dao;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Client;

/**
 * The ClientDao interface adds operations which can be performed with Client entities besides the CRUD operations
 * inherited from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface ClientDao extends GenericDao<Client> {

  /**
   * Gets a Client object using as an identifier a number of an account.
   * 
   * @param account the Account object which identifies the user
   * @return the user identified by its account
   */
  Client getClientByAccount(Account account);

}
