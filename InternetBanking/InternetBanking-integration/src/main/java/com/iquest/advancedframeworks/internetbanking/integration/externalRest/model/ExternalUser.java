package com.iquest.advancedframeworks.internetbanking.integration.externalRest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The model of an user received from an external source.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalUser {

  /**
   * The id of the user.
   */
  @XmlElement(name = "ID")
  private String id;

  /**
   * The cnp of the user.
   */
  @XmlElement(name = "CNP")
  private String cnp;

  /**
   * The first name of the user.
   */
  @XmlElement(name = "FirstName")
  private String firstName;

  /**
   * The last name of the user.
   */
  @XmlElement(name = "LastName")
  private String lastName;

  /**
   * The password of the user.
   */
  @XmlElement(name = "Pass")
  private String password;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCnp() {
    return cnp;
  }

  public void setCnp(String cnp) {
    this.cnp = cnp;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "ExternalUser [id=" + id + ", cnp=" + cnp + ", firstName=" + firstName + ", lastName=" + lastName
        + ", password=" + password + "]";
  }

}
