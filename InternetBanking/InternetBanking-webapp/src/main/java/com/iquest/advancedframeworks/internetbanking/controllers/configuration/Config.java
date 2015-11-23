package com.iquest.advancedframeworks.internetbanking.controllers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.iquest.advancedframeworks.internetbanking.controllers.configuration.springsecurity.SecurityConfiguration;

@Configuration
@ComponentScan("com.iquest.advancedframeworks")
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import({ SecurityConfiguration.class })
public class Config extends WebMvcConfigurationSupport {

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

}
