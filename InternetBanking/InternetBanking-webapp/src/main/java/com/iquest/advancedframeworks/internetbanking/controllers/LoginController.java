package com.iquest.advancedframeworks.internetbanking.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The TransactionController class represents a controller which interacts with
 * the login specific views.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
public class LoginController {

	/**
	 * Take decisions about the home page about a user home page. This method
	 * can be invoked if only the authenticated user has ROLE_USER as one of
	 * their granted authorities.
	 * 
	 * @param model
	 *            the model on with are set the attributes for the rendered view
	 * @return the user home page view name
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(Model model) {
		model.addAttribute("user", getPrincipal());
		return "user";
	}

	/**
	 * Take decisions about the home page about an admin home page. This method
	 * can be invoked if only the authenticated user has ROLE_ADMIN as one of
	 * their granted authorities.
	 * 
	 * @param model
	 *            the model on with are set the attributes for the rendered view
	 * @return the admin home page view name
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	/**
	 * Make decisions about the page which will be displayed if a user doesn't
	 * have access to a web page.
	 * 
	 * @param model
	 *            the Model object on which will be set the attributes for the
	 *            view
	 * @return the name of the view which will be displayed
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model) {
		model.addAttribute("user", getPrincipal());

		return "accessDenied";
	}

	/**
	 * Returns the login form.
	 * 
	 * @return the name of the login for which will be rendered
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	/**
	 * Makes logout for the current logged user.
	 * 
	 * @param request
	 *            the HttpServletRequest object
	 * @param response
	 *            HttpServletResponse
	 * @return the name of the view witch will be rendered
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	/**
	 * Gets the username of the authenticated user.
	 * 
	 * @return the username
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}