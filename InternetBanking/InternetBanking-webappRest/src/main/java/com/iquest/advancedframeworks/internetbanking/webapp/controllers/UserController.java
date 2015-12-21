//package com.iquest.advancedframeworks.internetbanking.webapp.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iquest.advancedframeworks.internetbanking.services.ClientService;
//import com.iquest.advancedframeworks.internetbanking.services.dto.ClientDto;
//import com.iquest.advancedframeworks.services.exceptions.UserNotFound;
//import com.iquest.advancedframeworks.services.exceptions.UserRegisteredException;
//
//@RestController
//@RequestMapping("/clients")
//public class UserController {
//
//  @Autowired
//  private ClientService clientService;
//
//  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//  public void createNewClient(@RequestBody ClientDto clientDto) throws UserRegisteredException {
//    clientService.insertUser(clientDto);
//  }
//
//  @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
//  public ClientDto getClientById(@PathVariable Integer clientId) throws UserNotFound {
//    ClientDto client = clientService.getUserbyId(clientId);
//    return client;
//  }
//
//  @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT)
//  public void updateClient(@PathVariable Integer clientId, @RequestBody ClientDto clientDto) {
//    //
//  }
//
//  @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
//  public void deleteClient(@PathVariable Integer clientId, @RequestBody ClientDto clientDto) {
//    // 
//  }
//
//}
