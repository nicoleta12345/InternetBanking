package com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration.springsecurity.OAuth2AuthorizationServerConfig;
import com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration.springsecurity.OAuth2ResourceServerConfig;
import com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration.springsecurity.SecurityConfiguration;

@Configuration
@ComponentScan("com.iquest.advancedframeworks")
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import({ SecurityConfiguration.class, OAuth2ResourceServerConfig.class, OAuth2AuthorizationServerConfig.class })
public class Config extends WebMvcConfigurerAdapter {

}
