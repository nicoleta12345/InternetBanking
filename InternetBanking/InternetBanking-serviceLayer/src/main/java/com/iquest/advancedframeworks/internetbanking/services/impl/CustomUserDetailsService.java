package com.iquest.advancedframeworks.internetbanking.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

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
   * THe repository for the User objects.
   */
  @Autowired
  private UserService userService;

  /**
   * Loads a user by it's username.
   */
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    User user = userService.getUserByUsername(username);

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    if (user == null) {
      System.out.println("User not found");
    }

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