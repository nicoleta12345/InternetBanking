package com.iquest.advancedframeworks.internetbanking.integration.externalRest;


import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

  @Autowired
  private SchedulerFactoryBean schedulerFactoryBean;

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    try {
      Scheduler sched = schedulerFactoryBean.getScheduler();
      sched.start();
      
      LOGGER.info("Started the external users integration scheduler");
    }
    catch (SchedulerException e) {
      // stay silent
      LOGGER.error(e.getClass().getSimpleName(), e.getStackTrace());
    }
  }
}
