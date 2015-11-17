package com.iquest.advancedframeworks.internetbanking.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iquest.advancedframeworks.internetbanking.model.Account;
import com.iquest.advancedframeworks.internetbanking.model.User;
import com.iquest.advancedframeworks.internetbanking.services.AccountService;
import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.UserService;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired
  AccountService accountService;

  @Autowired
  UserService userService;

  @Autowired
  DepositTransactionService depositTransactionService;

  @Autowired
  TransferTransactionService transferTransactionService;

  @Autowired
  WithdrawalTransactionService withdrawalTransactionService;

  @RequestMapping(value = "/depositForm", method = RequestMethod.GET)
  public String showFormDeposit() {
    return "deposit";
  }

  @RequestMapping(value = "/deposit", method = RequestMethod.POST)
  public void doDeposit(HttpSession session,
      @RequestParam String receiverNumberAccount,
      @RequestParam double valueSent, Model model) {

    model.addAttribute("receiver", receiverNumberAccount);
    model.addAttribute("value", valueSent);

    Account receiver = accountService.getAccountByNo(receiverNumberAccount);
    depositTransactionService.addTransaction(receiver, valueSent);

    // will return the name of the view with the transfer details
  }

  @RequestMapping(value = "/transferForm", method = RequestMethod.GET)
  public String showFormTransfer(HttpSession session, Model model) {
    User currentUser = (User) session.getAttribute("user");
    List<Account> userAccounts = userService.getAccountsNo(currentUser);

    model.addAttribute("accounts", userAccounts);

    return "transfer";
  }

  @RequestMapping(value = "/transfer", method = RequestMethod.POST)
  public void makeTransfer(HttpSession session,
      @RequestParam String senderNumberAccount,
      @RequestParam String receiverNumberAccount, @RequestParam double valueSent) {

    User currentUser = (User) session.getAttribute("user");
    Account sender = accountService.getAccountByNo(senderNumberAccount);
    Account receiver = accountService.getAccountByNo(receiverNumberAccount);

    if (userService.getUserByAccount(sender) != currentUser) {
      // throw...
    }

    transferTransactionService.addTransaction(sender, receiver,
        valueSent);

    // will return the name of the view with the transfer details
  }

  @RequestMapping(value = "/withdrawalForm", method = RequestMethod.GET)
  public String showFormwithdrawal(HttpSession session, Model model) {
    User currentUser = (User) session.getAttribute("user");
    List<Account> userAccounts = userService.getAccountsNo(currentUser);

    model.addAttribute("accounts", userAccounts);

    return "withdrawal";
  }

  @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
  public void doWithdrawal(HttpSession session,
      @RequestParam String senderNumberAccount, @RequestParam double valueSent) {

    User currentUser = (User) session.getAttribute("user");
    Account sender = accountService.getAccountByNo(senderNumberAccount);
    
    if (userService.getUserByAccount(sender) != currentUser) {
      // throw...
    }
    withdrawalTransactionService.addTransaction(sender, valueSent);

    // will return the name of the view with the transfer details
  }

}
