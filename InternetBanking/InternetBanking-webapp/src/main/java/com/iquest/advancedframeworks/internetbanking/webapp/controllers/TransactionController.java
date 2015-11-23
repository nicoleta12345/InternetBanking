package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iquest.advancedframeworks.internetbanking.persistence.model.Account;
import com.iquest.advancedframeworks.internetbanking.persistence.model.User;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.UserService;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;

/**
 * The TransactionController class represents a controller which interacts with
 * the transaction specific views and the service implementations. It is mapped
 * to a certain url.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {

	/**
	 * The account services.
	 */
	@Autowired
	AccountService accountService;

	/**
	 * The user services.
	 */
	@Autowired
	UserService userService;

	/**
	 * The deposit transaction services.
	 */
	@Autowired
	DepositTransactionService depositTransactionService;

	/**
	 * The transfer transaction services.
	 */
	@Autowired
	TransferTransactionService transferTransactionService;

	/**
	 * The withdrawal transaction services
	 */
	@Autowired
	WithdrawalTransactionService withdrawalTransactionService;

	/**
	 * Shows a form to make a deposit transaction.
	 * 
	 * @return the name of the view which will be rendered
	 */
	@RequestMapping(value = "/depositForm", method = RequestMethod.GET)
	public String showFormDeposit() {
		return "deposit";
	}

	/**
	 * Makes a deposit transaction.
	 * 
	 * @param session
	 * @param receiverNumberAccount
	 *            the number account of the receiver
	 * @param valueSent
	 *            the value sent
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public void doDeposit(HttpSession session, @RequestParam String receiverNumberAccount,
			@RequestParam double valueSent, Model model) {

		model.addAttribute("receiver", receiverNumberAccount);
		model.addAttribute("value", valueSent);

		Account receiver = accountService.getAccountByNo(receiverNumberAccount);
		depositTransactionService.addTransaction(receiver, valueSent);

		// will return the name of the view with the transfer details
	}

	/**
	 * Shows a form to make a transfer transaction.
	 * 
	 * @return the name of the view which will be rendered
	 */
	@RequestMapping(value = "/transferForm", method = RequestMethod.GET)
	public String showFormTransfer(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		User currentUser = userService.getUserByUsername(username);
		List<Account> userAccounts = accountService.getAccountsNo(currentUser);

		model.addAttribute("accounts", userAccounts);

		return "transfer";
	}

	/**
	 * Makes a transfer transaction. It first checks if the sender account is
	 * one of the current user accounts.
	 * 
	 * @param session
	 * @param senderNumberAccount
	 *            the number account of the sender
	 * @param receiverNumberAccount
	 *            the number account of the receiver
	 * @param valueSent
	 *            the value sent
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public void makeTransfer(HttpSession session, @RequestParam String senderNumberAccount,
			@RequestParam String receiverNumberAccount, @RequestParam double valueSent) {
		Account sender = accountService.getAccountByNo(senderNumberAccount);
		Account receiver = accountService.getAccountByNo(receiverNumberAccount);

		String username = (String) session.getAttribute("username");
		User senderUser = userService.getUserByAccount(sender);

		if (senderUser.getUsername() != username) {
			// model.addAttribute("errorMessage", "There was a problem, please
			// try again!");

			// return "withdrawal";
		}

		transferTransactionService.addTransaction(sender, receiver, valueSent);

		// will return the name of the view with the transfer details
	}

	/**
	 * Shows a form to make a withdrawal transaction.
	 * 
	 * @return the name of the view which will be rendered
	 */
	@RequestMapping(value = "/withdrawalForm", method = RequestMethod.GET)
	public String showFormWithdrawal(HttpSession session, Model model) {
	  String username = (String) session.getAttribute("username");
	  System.out.println("------username " + username);
    User currentUser = userService.getUserByUsername(username);
    System.out.println("-----current user" + currentUser);
    List<Account> userAccounts = accountService.getAccountsNo(currentUser);

    System.out.println("------user accounts: " +  userAccounts);
		model.addAttribute("accounts", userAccounts);

		return "withdrawal";
	}

	/**
	 * Makes a withdrawal transaction. It first checks if the sender account is
	 * one of the current user accounts.
	 * 
	 * @param session
	 * @param senderNumberAccount
	 *            the number account of the sender
	 * @param valueSent
	 *            the value sent
	 */
	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	public String doWithdrawal(HttpSession session, @RequestParam String senderNumberAccount,
			@RequestParam double valueSent, Model model) {

		Account sender = accountService.getAccountByNo(senderNumberAccount);

		String username = (String) session.getAttribute("username");
		User senderUser = userService.getUserByAccount(sender);

		if (senderUser.getUsername() != username) {
			model.addAttribute("errorMessage", "There was a problem, please try again!");

			return showFormWithdrawal(session, model);
		}

		withdrawalTransactionService.addTransaction(sender, valueSent);

		model.addAttribute("accountNo", senderNumberAccount);
		model.addAttribute("value", valueSent);

		return "withdrawalDetails";
	}

}
