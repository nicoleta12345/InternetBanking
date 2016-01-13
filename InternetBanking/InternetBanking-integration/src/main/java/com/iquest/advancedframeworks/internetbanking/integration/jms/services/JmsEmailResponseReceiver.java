package com.iquest.advancedframeworks.internetbanking.integration.jms.services;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.integration.jms.model.response.EmailResponse;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.RegistrationUserEmail;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;

/**
 * The JmsEmailResponseReceiver class is a listener on a jms queue.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
@Scope("prototype")
public class JmsEmailResponseReceiver extends Thread {

  /**
   * The logger for the JmsEmailService class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(JmsEmailResponseReceiver.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private RegistrationEmailService registrationEmailService;

  /**
   * The JmsTemplate object used to send the request.
   */
  @Autowired
  private JmsTemplate jmsTemplate;

  @Scheduled(fixedDelay = 30000)
  @Override
  public void run() {
    ConcurrentMap<User, RegistrationUserEmail> map = getCorrelationId();

    if (map != null) {
      for (Entry<User, RegistrationUserEmail> entry : map.entrySet()) {
        System.out.println("correlationId " + entry.getValue().getCorrelationId());
        jmsTemplate.setReceiveTimeout(360);// JmsTemplate.RECEIVE_TIMEOUT_NO_WAIT);
        EmailResponse response = (EmailResponse) jmsTemplate.receiveSelectedAndConvert("IQUEST.UNIV.EMAIL.RESPONSE",
            "JMSCorrelationID='" + entry.getValue().getCorrelationId() + "'");
        System.out.println("JMSCorrelationID='" + entry.getValue().getCorrelationId() + "'");

        System.out.println(response);
        if (response != null)
          if (response.getErrorCode().equals("000")) {
            LOGGER.info(
                "The user with the id" + entry.getKey().getId() + "received the registration confirmation email");
            updateUser(response, entry);
          }
          else {
            LOGGER.error(response.getErrorMessage());
          }
      }
    }

  }

  @Transactional
  private void updateUser(EmailResponse response, Entry<User, RegistrationUserEmail> entry) {
    entry.getValue().setStatus(response.getStatus());

    registrationEmailService.update(entry.getValue());
  }

  /**
   * Gets a map with the information about the responses which should be waited for.
   * 
   * @return a Concurrent map with the waited responses and the users
   */
  private ConcurrentMap<User, RegistrationUserEmail> getCorrelationId() {
    List<User> usersWithPendingConfimation = userDao.getUsersWithPendingConfirmation();
    ConcurrentMap<User, RegistrationUserEmail> map = new ConcurrentHashMap<>();

    if (usersWithPendingConfimation != null) {

      for (User user : usersWithPendingConfimation) {
        map.put(user, user.getConfirmationOfRegistration());
      }
    }

    return map;
  }

}
