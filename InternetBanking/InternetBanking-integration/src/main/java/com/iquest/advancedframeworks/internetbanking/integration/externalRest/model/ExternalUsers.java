package com.iquest.advancedframeworks.internetbanking.integration.externalRest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The ExternalUsers class represents an object received from an external source which contains details about users.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@XmlRootElement(name = "Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalUsers {

  /**
   * The list with the external users details.
   */
  @XmlElement(name = "User")
  private List<ExternalUser> users;

  public List<ExternalUser> getUsers() {
    return users;
  }

  public void setUsers(List<ExternalUser> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "ExternalUsers [users=" + users + "]";
  }

}
