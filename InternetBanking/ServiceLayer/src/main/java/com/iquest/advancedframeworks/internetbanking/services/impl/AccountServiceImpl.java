package com.iquest.advancedframeworks.internetbanking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;

/**
 * The AccountServiceImpl class implements AccountService interface and calls an
 * AccountDAo object to perform operations with Accounts objects.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	/**
	 * An AccountDao injected object used to perform operations with Account
	 * objects.
	 */
	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional
	public void createAccount(Account account) {
		accountDao.createAccount(account);
	}
	
	@Override
	public Account getAccountByNo(String accountNo) {
		return accountDao.getAccountByNo(accountNo);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountDao.updateAccount(account);
	}

}
