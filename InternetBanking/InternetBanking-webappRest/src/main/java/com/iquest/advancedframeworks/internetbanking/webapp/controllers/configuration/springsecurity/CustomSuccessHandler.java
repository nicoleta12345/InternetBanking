package com.iquest.advancedframeworks.internetbanking.webapp.controllers.configuration.springsecurity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    setUserOnSession(request.getSession());
    String targetUrl = determineTargetUrl(authentication);

    if (response.isCommitted()) {
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  protected String determineTargetUrl(Authentication authentication) {
    String url = "";
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    List<String> roles = new ArrayList<String>();

    for (GrantedAuthority a : authorities) {
      roles.add(a.getAuthority());
    }

    if (isUser(roles)) {
      url = "/user";
    }
    else if (isAdmin(roles)) {
      url = "/admin";
    }
    else if (isUser(roles)) {
      url = "/home";
    }
    else {
      url = "/accessDenied";
    }

    return url;
  }

  /**
   * Sets the userId in the HttpSession object.
   * 
   * @param session The HttpSession object
   */
  void setUserOnSession(HttpSession session) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername(); 

    session.setAttribute("username", username);
  }

  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
    this.redirectStrategy = redirectStrategy;
  }

  protected RedirectStrategy getRedirectStrategy() {
    return redirectStrategy;
  }

  private boolean isUser(List<String> roles) {
    if (roles.contains("ROLE_USER")) {
      return true;
    }
    return false;
  }

  private boolean isAdmin(List<String> roles) {
    System.out.println("Roles: " + roles);
    if (roles.contains("ROLE_ADMIN")) {
      return true;
    }
    return false;
  }

}