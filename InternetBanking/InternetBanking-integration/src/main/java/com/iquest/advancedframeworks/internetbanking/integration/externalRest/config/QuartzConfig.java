package com.iquest.advancedframeworks.internetbanking.integration.externalRest.config;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.iquest.advancedframeworks.internetbanking.integration.externalRest.quartz.ExternalUsersIntegration;
import com.iquest.advancedframeworks.internetbanking.integration.externalRest.quartz.UsersIntegrationJob;
import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;

/**
 * The configuration needed for quartz integration.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Configuration
@ComponentScan({ "com.iquest.advancedframeworks.internetbanking.integration.externalRest" })
public class QuartzConfig {

  /**
   * The userDao repository
   */
  @Autowired
  UserDao userDao;

  /**
   * The service used to persist users received from an extrenal source.
   */
  @Autowired
  ExternalUsersIntegration externalUsersIntegration;

  /**
   * Sets the job class and injects bean references using jobFataAsMap.
   * 
   * @return anew configured JobDetailFactoryBean
   */
  @Bean
  public JobDetailFactoryBean jobDetail() {
    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(UsersIntegrationJob.class);
    jobDetailFactory.setDurability(true);

    Map<String, UserDao> jobDataAsMap = new HashMap<>();
    jobDataAsMap.put("UserDao", userDao);
    jobDetailFactory.setJobDataAsMap(jobDataAsMap);

    return jobDetailFactory;
  }

  /**
   * Configures a trigger to run a job at every half an hour.
   * 
   * @param job the job which will be executed
   * @return a new configured SimpleTriggerFactoryBean
   */
  @Bean
  public SimpleTriggerFactoryBean trigger(JobDetail job) {
    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
    trigger.setJobDetail(job);
    trigger.setRepeatInterval(1800L * 1000L);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);

    return trigger;
  }

  /**
   * Sets the job and the trigger for a scheduler and also injects bean reference using schedulerContextAsMap.
   * 
   * @param trigger the trigger which will fire the execution of the job
   * @param job the job which will be executed
   * @return a new configured SchedulerFactoryBean
   */
  @Bean
  public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
    SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
    schedulerFactory.setJobDetails(job);
    schedulerFactory.setTriggers(trigger);

    Map<String, ExternalUsersIntegration> schedulerContextAsMap = new HashMap<>();
    schedulerContextAsMap.put("externalUsersIntegration", externalUsersIntegration);
    schedulerFactory.setSchedulerContextAsMap(schedulerContextAsMap);

    return schedulerFactory;
  }

}
