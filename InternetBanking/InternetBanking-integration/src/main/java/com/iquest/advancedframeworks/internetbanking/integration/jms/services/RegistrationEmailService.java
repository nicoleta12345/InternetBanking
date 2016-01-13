package com.iquest.advancedframeworks.internetbanking.integration.jms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.ConfirmationMailDao;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.exception.EntityDeletedException;
import com.iquest.advancedframeworks.internetbanking.persistence.model.RegistrationUserEmail;

/**
 * The RegistrationEmailService class is used to offer services to persist the state of the jms messages..
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class RegistrationEmailService {

  /**
   * The repository of the ConfirmationMail objects.
   */
  @Autowired
  private ConfirmationMailDao confirmationMailDao;

  /**
   * Updates the state of a confirmation mail.
   * 
   * @param regUserEmail the details about the registration confirmation email
   */
  @Transactional
  public void update(RegistrationUserEmail regUserEmail) {
    try {
      confirmationMailDao.update(regUserEmail);
    }
    catch (EntityDeletedException e) {
      // stay silent }
    }
  }

}
