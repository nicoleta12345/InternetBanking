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
import com.iquest.advancedframeworks.internetbanking.persistence.model.SavingsAccount;

/**
 * The IncomeScheduler class is used to add at the interest of the account to their value. It is a job which will run at
 * a specified interval.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class IncomeScheduler {

  /**
   * Logger instance used to log information from the IncomeScheduler.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(IncomeScheduler.class);

  /**
   * The accounts repository.
   */
  @Autowired
  private AccountDao accountDao;

  /**
   * Computes the interest of every savings account and adds it to the value of the account. This method runs at 12
   * o'clock on the first day of the every month.
   */
  @Scheduled(cron = "0 0 12 1 * ?")
  @Transactional
  public void addInterest() {
    List<SavingsAccount> savingsAccounts = accountDao.getAllSavingsAccounts();

    for (SavingsAccount savingsAccount : savingsAccounts) {
      double currentAmount = savingsAccount.getAmount();
      double income = (double) (currentAmount * (savingsAccount.getInterestPercentage() / 100.0f));

      savingsAccount.setAmount(currentAmount + income);
      try {
        accountDao.update(savingsAccount);
      }
      catch (EntityDeletedException e) {
        LOGGER.error(e.getClass().getSimpleName(), e.getMessage());
      }
    }
  }

}
