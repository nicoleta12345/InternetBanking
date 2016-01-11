package com.iquest.advancedframeworks.internetbanking.services.impl.schedulers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan({ "com.iquest.advancedframeworks.internetbanking.services.impl.schedulers" })
public class SchedulersConfig {

}
