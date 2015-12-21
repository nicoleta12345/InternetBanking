package com.iquest.advancedframeworks.internetbanking.persistence.dao;

import java.util.List;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Transaction;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Transfer;

/**
 * The TransactionDao interface adds operations which can be performed with Transactions entities besides the CRUD
 * operations inherited from the GenericDao interface.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public interface TransactionDao extends GenericDao<Transaction> {

  /**
   * Gets the pending transfer transactions.
   * 
   * @return a list with the pending transactions
   */
  List<Transfer> getPendingTransactions();
  
}
