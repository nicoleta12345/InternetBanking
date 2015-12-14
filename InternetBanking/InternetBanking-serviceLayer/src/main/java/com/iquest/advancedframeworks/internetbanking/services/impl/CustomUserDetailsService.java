package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The CustomUserDetailsService class represent a custom implementation for UserDetailsService. It is used to
 * authenticate a user in the application.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  /**
   * Logger instance used to log information from the CustomUserDetailsService.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

  /**
   * The repository for the Client objects.
   */
  @Autowired
  private UserDao userDao;

  /**
   * Loads a client by it's username.
   */
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    
    System.out.println("load by usernmae");
    User user = (User) userDao.getUserByUsername(username);

     if(user == null) {
       //throw new UserNotFound("The user could not be found");
       LOGGER.error("UserNotFound! The user with the username: " + username + "could not be found");
     }

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled,
        accountNonExpired, credentialsNonExpired, accountNonLocked, getGrantedAuthorities(user));
  }

  /**
   * Gets the Granted Authorities for an user.
   * 
   * @param user the user for witch the granted authorities are requested
   * @return a list with the user authorities
   */
  private List<GrantedAuthority> getGrantedAuthorities(User user) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    for (UserRole userRole : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
    }

    return authorities;
  }

}