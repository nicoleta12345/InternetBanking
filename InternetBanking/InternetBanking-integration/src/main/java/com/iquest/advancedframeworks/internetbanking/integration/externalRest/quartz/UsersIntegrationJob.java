package com.iquest.advancedframeworks.internetbanking.integration.externalRest.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iquest.advancedframeworks.internetbanking.integration.externalRest.model.ExternalUsers;

@Service
public class UsersIntegrationJob extends QuartzJobBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(UsersIntegrationJob.class);

  private ExternalUsersIntegration externalUsersIntegration;

  public ExternalUsersIntegration getExternalUsersIntegration() {
    return externalUsersIntegration;
  }

  public void setExternalUsersIntegration(ExternalUsersIntegration externalUsersIntegration) {
    this.externalUsersIntegration = externalUsersIntegration;
  }

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    RestTemplate restTemplate = new RestTemplate();
    ExternalUsers externalUsers = restTemplate.getForObject(
        "http://localhost:8081/rest-server-simulator/users?USER-TYPE=USER", ExternalUsers.class);

    externalUsersIntegration.persistUsers(externalUsers);
    LOGGER.debug(externalUsers.toString());
  }

}
