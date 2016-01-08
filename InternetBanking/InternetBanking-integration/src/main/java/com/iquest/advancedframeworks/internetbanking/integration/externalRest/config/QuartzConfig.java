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

@Configuration
@ComponentScan({ "com.iquest.advancedframeworks.internetbanking.integration.externalRest" })
public class QuartzConfig {

  @Autowired
  UserDao userDao;

  @Autowired
  ExternalUsersIntegration externalUsersIntegration;

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

  @Bean
  public SimpleTriggerFactoryBean trigger(JobDetail job) {
    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
    trigger.setJobDetail(job);
    trigger.setRepeatInterval(1800L * 1000L);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);

    return trigger;
  }

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
