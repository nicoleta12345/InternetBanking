package com.iquest.advancedframeworks.internetbanking.integration.externalRest.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iquest.advancedframeworks.internetbanking.integration.externalRest.model.ExternalUsers;

/**
 * The UsersIntegrationJob class is a quartz job. It makes a call to an external endpoint to get details about new
 * users.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service
public class UsersIntegrationJob extends QuartzJobBean {

  /**
   * The logger for the UsersIntegrationJob class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UsersIntegrationJob.class);

  /**
   * Used to persist the information received.
   */
  private ExternalUsersIntegration externalUsersIntegration;

  /**
   * The url used to make the external call.
   */
  private final String REST_ENDPOINT = "http://localhost:8081/rest-server-simulator/users?USER-TYPE=USER";

  /**
   * Makes a call to an external rest endpoint and gets a list of users which will be persisted.
   */
  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    RestTemplate restTemplate = new RestTemplate();
    ExternalUsers externalUsers = restTemplate.getForObject(REST_ENDPOINT, ExternalUsers.class);

    externalUsersIntegration.persistUsers(externalUsers);
    LOGGER.debug("Received content" + externalUsers.toString());
  }

  public ExternalUsersIntegration getExternalUsersIntegration() {
    return externalUsersIntegration;
  }

  public void setExternalUsersIntegration(ExternalUsersIntegration externalUsersIntegration) {
    this.externalUsersIntegration = externalUsersIntegration;
  }

}
