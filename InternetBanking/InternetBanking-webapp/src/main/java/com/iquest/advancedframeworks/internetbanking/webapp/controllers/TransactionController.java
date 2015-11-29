package com.iquest.advancedframeworks.internetbanking.webapp.controllers;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iquest.advancedframeworks.internetbanking.services.DepositTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.TransferTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.WithdrawalTransactionService;
import com.iquest.advancedframeworks.internetbanking.services.dto.DepositTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransactionAccounts;
import com.iquest.advancedframeworks.internetbanking.services.dto.TransferTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.dto.WithdrawalTransactionDto;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountAccessDenied;
import com.iquest.advancedframeworks.internetbanking.services.exceptions.AccountNotFound;

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
  @Secured("ROLE_USER")
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
  public String doDeposit(HttpSession session, @ModelAttribute DepositTransactionDto depositTransaction, Model model) {
    try {
      depositTransactionService.addTransaction(depositTransaction);
    }
    catch (AccountNotFoundException | AccountNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    model.addAttribute("message", "The transaction was made!");
    return "operationSuccess";
  }

  /**
   * Shows a form to make a transfer transaction.
   * 
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/transferForm", method = RequestMethod.GET)
  public String showFormTransfer(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    
    TransactionAccounts userAccountsDto = transferTransactionService.getFormData(username);
    model.addAttribute("userAccountsDto", userAccountsDto);
    
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
  @Secured("ROLE_USER")
  @RequestMapping(value = "/transfer", method = RequestMethod.POST)
  public String makeTransfer(HttpSession session, @ModelAttribute TransferTransactionDto transferTransaction,
      Model model) {
    String username = (String) session.getAttribute("username");

    try {
      transferTransactionService.addTransaction(transferTransaction, username);
    }
    catch (AccountAccessDenied e) {
      model.addAttribute("user", username);
      return "accessDenied";
    }
    catch (AccountNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    model.addAttribute("message", "The transfer was made!");
    return "operationSuccess";
  }

  /**
   * Shows a form to make a withdrawal transaction.
   * 
   * @return the name of the view which will be rendered
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/withdrawalForm", method = RequestMethod.GET)
  public String showFormWithdrawal(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    
    TransactionAccounts userAccountsDto = withdrawalTransactionService.getFormData(username);
    model.addAttribute("userAccountsDto", userAccountsDto);
    
    return "withdrawal";
  }

  /**
   * Makes a withdrawal transaction. It first checks if the sender account is one of the current user accounts.
   * 
   * @param session
   * @param senderNumberAccount the number account of the sender
   * @param valueSent the value sent
   */
  @Secured("ROLE_USER")
  @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
  public String doWithdrawal(HttpSession session, @ModelAttribute WithdrawalTransactionDto withdrawalTransaction,
      Model model) {
    String currentUserUsername = (String) session.getAttribute("username");

    try {
      withdrawalTransactionService.addTransaction(withdrawalTransaction, currentUserUsername);
    }
    catch (AccountAccessDenied e) {
      model.addAttribute("user", currentUserUsername);
      return "accessDenied";
    }
    catch (AccountNotFound e) {
      model.addAttribute("errorMessage", "Something went wrong, please try again later!");
      return "error";
    }

    model.addAttribute("message", "The transaction was made!");
    return "operationSuccess";
  }

}
