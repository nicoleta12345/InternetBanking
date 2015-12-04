package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.TransactionDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Transaction;

/**
 * The TransactionDaoImpl class implements TransactionDao interface and extends the abstract class GenericDaoImpl taking
 * benefits of its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao {

}
