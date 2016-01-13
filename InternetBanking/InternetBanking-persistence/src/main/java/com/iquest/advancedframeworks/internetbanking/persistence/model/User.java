package com.iquest.advancedframeworks.internetbanking.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.validator.constraints.Email;

import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.Cnp;
import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.UserAge;

/**
 * The abstract class User contains the basic details about a user for an online banking application.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "USERS")
@DiscriminatorOptions(force = true)
public class User {

  /**
   * The id of the user. It represents the primary key for the table which will be generated.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The user code used for authentication.
   */
  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  /**
   * The password used for authentication.
   */
  @Column(name = "PASSWORD", nullable = false)
  private String password;

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
   * The email of the user.
   */
  @Email
  private String email;

  @OneToOne
  @JoinColumn(name = "CONFIRMATION_OF_REGISTRATION_ID")
  private RegistrationUserEmail confirmationOfRegistration;

  /**
   * The age of the user.
   */
  @UserAge
  @Column(name = "AGE")
  private int age;

  /**
   * The address of the user. The address primary key will be set as a foreign key into the table generated for this
   * class.
   */
  @OneToOne
  @JoinColumn(name = "ADDRESS_ID")
  private Address address;

  /**
   * The user role for authentication.
   */
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(joinColumns = @JoinColumn(name = "ROLE_ID") , inverseJoinColumns = @JoinColumn(name = "USER_ID") )
  private Set<UserRole> roles;

  public void setAge(int age) {
    this.age = age;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getCnp() {
    return cnp;
  }

  public void setCnp(String cnp) {
    this.cnp = cnp;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public RegistrationUserEmail getConfirmationOfRegistration() {
    return confirmationOfRegistration;
  }

  public void setConfirmationOfRegistration(RegistrationUserEmail confirmationOfRegistration) {
    this.confirmationOfRegistration = confirmationOfRegistration;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
        + ", lastName=" + lastName + ", cnp=" + cnp + ", email=" + email + ", age=" + age + ", address=" + address
        + ", roles=" + roles + "]";
  }

}
