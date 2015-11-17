package com.iquest.advancedframeworks.internetbanking.controllers;

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
import com.iquest.advancedframeworks.internetbanking.services.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

  @Autowired
  AccountService accountService;

  @Autowired
  UserService userService;

  @RequestMapping(value = "/accountForm", method = RequestMethod.GET)
  public String showFormGetIdUser() {
    return "account";
  }

  @RequestMapping(value = "/getAccount", method = RequestMethod.POST)
  public String getUser(HttpSession session,
      @RequestParam String accountNumber, Model model) {
    Account account = accountService.getAccountByNo(accountNumber);

    User currentUser = (User) session.getAttribute("user");
    if (userService.getUserByAccount(account) != currentUser) {
      // throw ...
    }
    model.addAttribute("accountNo", accountNumber);
    model.addAttribute("amount", account.getAmount());

    return "accountDetails";
  }

}
