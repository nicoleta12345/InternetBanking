package com.iquest.advancedframeworks.internetbanking.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.Address;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.model.UserDetails;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.UserService;

/**
 * The UserController class represents a controller which interacts with the
 * views and the service implementations. It is mapped to a certain url.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * An injected UserService object used to perform operations with User
	 * objects.
	 */
	@Autowired
	UserService userService;

	/**
	 * An injected AccountService object used to perform operations with Account
	 * objects.
	 */
	@Autowired
	AccountService accountService;

	/**
	 * Adds a user. For the moment the user details are harcodated. The details
	 * will be replaced with parameters given to the method.
	 */
	@RequestMapping("/addUser")
	public void insertUser() {

		// hardcoded user datails
		User user = new User();
		Account account = new Account();
		UserDetails userDetails = new UserDetails();
		Address address = new Address();
		List<Account> accounts = new ArrayList<>();

		account.setAccountNumber(Long.valueOf(123));
		account.setAmount(100.5);
		accounts.add(account);

		address.setStreetName("Strada Merilor");
		address.setStreetNumber(21);
		address.setTown("Craiova");
		address.setPostalCode(Long.valueOf(123));

		userDetails.setFirstName("Ana");
		userDetails.setLastName("Popescu");
		userDetails.setCnp("12345678901234");
		userDetails.setAge(22);
		userDetails.setEmail("lala@yahoo.com");
		userDetails.setAddress(address);

		user.setPassword("pass");
		user.setUserCode("456");
		user.setAccounts(accounts);
		user.setUserDetails(userDetails);

		// creates an account
		accountService.createAccount(account);
		// inserts a user into a database
		userService.insertUser(user, userDetails, address);
	}

	/**
	 * Displays a form to get the id of an user, whose informations will be
	 * displayed later.
	 * 
	 * @return a ModelAndView object with a new view.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("user");
	}

	/**
	 * Displays information about a certain user identified by a given id.
	 * 
	 * @param id
	 *            the identifier of the user
	 * @return a ModelAndView object with a rendered view which contains details
	 *         about the user
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public ModelAndView getUser(@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView("userInfo");
		User user = userService.getUserbyId(id);

		modelAndView.addObject("firstName", user.getUserDetails().getFirstName());
		modelAndView.addObject("lastName", user.getUserDetails().getLastName());
		modelAndView.addObject("cnp", user.getUserDetails().getCnp());
		modelAndView.addObject("accountNumber", user.getAccounts().get(0).getAccountNumber());
		modelAndView.addObject("amount", user.getAccounts().get(0).getAmount());

		return modelAndView;
	}

}
