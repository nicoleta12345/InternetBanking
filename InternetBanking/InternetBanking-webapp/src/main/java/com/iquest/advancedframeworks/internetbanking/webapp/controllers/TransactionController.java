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
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.UserNotFound;

/**
 * The TransactionController class represents a controller which interacts with the transaction specific views and the
 * service implementations. It is mapped to a certain url.
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
   * @param receiverNumberAccount the number account of the receiver
   * @param valueSent the value sent
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/deposit", method = RequestMethod.POST)
  public String doDeposit(HttpSession session, @RequestParam String receiverNumberAccount,
      @RequestParam double valueSent, Model model) {

    model.addAttribute("receiver", receiverNumberAccount);
    model.addAttribute("value", valueSent);

    Account receiver = null;
    try {
      receiver = accountService.getAccountByNo(receiverNumberAccount);
    }
    catch (AccountNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    depositTransactionService.addTransaction(receiver, valueSent);

    return "depositDetails";
  }

  /**
   * Shows a form to make a transfer transaction.
   * 
   * @return the name of the view which will be rendered
   */
  @RequestMapping(value = "/transferForm", method = RequestMethod.GET)
  public String showFormTransfer(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    User currentUser;
    List<Account> userAccounts;

    try {
      currentUser = userService.getUserByUsername(username);
      userAccounts = accountService.getAccountsNo(currentUser);
    }
    catch (UserNotFound | AccountNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    model.addAttribute("accounts", userAccounts);

    return "transfer";
  }

  /**
   * Makes a transfer transaction. It first checks if the sender account is one of the current user accounts.
   * 
   * @param session
   * @param senderNumberAccount the number account of the sender
   * @param receiverNumberAccount the number account of the receiver
   * @param valueSent the value sent
   */
  @RequestMapping(value = "/transfer", method = RequestMethod.POST)
  public String makeTransfer(HttpSession session, @RequestParam String senderNumberAccount,
      @RequestParam String receiverNumberAccount, @RequestParam double valueSent, Model model) {
    String username = (String) session.getAttribute("username");
    Account sender = null;
    Account receiver;
    User senderUser;

    try {
      sender = accountService.getAccountByNo(senderNumberAccount);
      receiver = accountService.getAccountByNo(receiverNumberAccount);
      senderUser = userService.getUserByAccount(sender);
    }
    catch (AccountNotFound | UserNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    if (senderUser.getUsername() != username) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    transferTransactionService.addTransaction(sender, receiver, valueSent);

    return "transferDetails";
  }

  /**
   * Shows a form to make a withdrawal transaction.
   * 
   * @return the name of the view which will be rendered
   */
  @RequestMapping(value = "/withdrawalForm", method = RequestMethod.GET)
  public String showFormWithdrawal(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    User currentUser;
    List<Account> userAccounts;
    
    try {
      currentUser = userService.getUserByUsername(username);
      userAccounts = accountService.getAccountsNo(currentUser);
    }
    catch (UserNotFound | AccountNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }    

    model.addAttribute("accounts", userAccounts);

    return "withdrawal";
  }

  /**
   * Makes a withdrawal transaction. It first checks if the sender account is one of the current user accounts.
   * 
   * @param session
   * @param senderNumberAccount the number account of the sender
   * @param valueSent the value sent
   */
  @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
  public String doWithdrawal(HttpSession session, @RequestParam String senderNumberAccount,
      @RequestParam double valueSent, Model model) {
    String username = (String) session.getAttribute("username");
    User senderUser;
    
    Account sender;
    try {
      sender = accountService.getAccountByNo(senderNumberAccount);
      senderUser = userService.getUserByAccount(sender);
    }
    catch (AccountNotFound | UserNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

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
