package com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {
		"com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration.springsecurity" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/home")
				.access("hasRole('USER') or hasRole('ADMIN')").antMatchers("/user/**").access("hasRole('USER')")
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/transaction/**")
				.access("hasRole('USER')")
				.antMatchers("/account/**")
				.access("hasRole('USER')").
				and().formLogin().loginPage("/login")

				.successHandler(customSuccessHandler).usernameParameter("username").passwordParameter("password").and()
				.csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");

	}

}
