package com.iquest.advancedframeworks.internetbanking.services.impl.schedulers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.AccountDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;

/**
 * The MaintenanceCostScheduler class
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class MaintenanceCostScheduler {

  /**
   * Logger instance used to log information from the MaintenanceCostScheduler.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceCostScheduler.class);

  /**
   * The Account objects repository.
   */
  @Autowired
  private AccountDao accountDao;

  /**
   * Computes the maintenance cost for every account and takes this value from the amount of the account.This method
   * runs at 13 o'clock on the first day of the every month.
   */
  @Scheduled(cron = "0 0 13 1 * ?")
  @Transactional
  public void getMaintenanceCost() {
    List<Account> accounts = accountDao.getAllAccounts();

    for (Account account : accounts) {
      double currentAmount = account.getAmount();
      double maintenanceCost = account.getMentenanceCost();

      account.setAmount(currentAmount + maintenanceCost);
      try {
        accountDao.update(account);
      }
      catch (EntityDeletedException e) {
        LOGGER.error(e.getClass().getSimpleName(), e.getMessage());
      }

    }
  }
}
