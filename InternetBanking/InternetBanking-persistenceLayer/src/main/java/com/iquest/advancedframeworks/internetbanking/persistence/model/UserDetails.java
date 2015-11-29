package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.Cnp;
import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.UserAge;

/**
 * The UserDetails class represents details about the user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

  /**
   * The id of the UserDetails objects. It represents the primary key into the table which will be generated for this
   * class.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The first name of the user.
   */
  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;

  /**
   * The last name of the user.
   */
  @Column(name = "LAST_NAME", nullable = false)
  private String lastName;

  /**
   * The cnp of the user.
   */
  @Cnp
  @Column(name = "CNP", nullable = false, unique = true)
  private String cnp;

  /**
   * The age of the user.
   */
  @UserAge
  @Column(name = "AGE")
  private Integer age;

  /**
   * The address of the user. The address primary key will be set as a foreign key into the table generated for this
   * class.
   */
  @OneToOne
  @JoinColumn(name = "ADDRESS_ID")
  private Address address;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

}
