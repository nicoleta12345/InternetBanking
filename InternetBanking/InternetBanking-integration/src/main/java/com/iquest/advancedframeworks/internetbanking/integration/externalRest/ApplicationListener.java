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

/**
 * The ApplicationLitener class starts a scheduler at start up.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Component
public class ApplicationListener {

  /**
   * The logger of the ApplicationListener class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

  /**
   * The factory of the Scheduler object.
   */
  @Autowired
  private SchedulerFactoryBean schedulerFactoryBean;

  /**
   * Listens to a context refreshed event and starts the scheduler.
   * 
   * @param event the event which triggers the method call
   */
  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    try {
      Scheduler sched = schedulerFactoryBean.getScheduler();
      sched.start();

      LOGGER.info("Started the external users integration scheduler");
    } catch (SchedulerException e) {
      LOGGER.error(e.getClass().getSimpleName(), e.getStackTrace());
    }
  }
}
