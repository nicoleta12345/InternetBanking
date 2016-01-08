package com.iquest.advancedframeworks.internetbanking.services.dto;

import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.Cnp;
import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.UserAge;

/**
 * The UserDto class represents a DTO which contains details about a user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class UserDto {

  /**
   * The first name of the user.
   */
  @NotEmpty(message = "{clientRegistrationForm.firstNameRequired}")
  private String firstName;

  /**
   * The last name of the user.
   */
  @NotEmpty(message = "{clientRegistrationForm.lastNameRequired}")
  private String lastName;

  /**
   * The cnp of the user.
   */
  @Cnp
  @NotEmpty(message = "{clientRegistrationForm.cnpRequired}")
  private String cnp;

  /**
   * The email of the user.
   */
  @Email(message = "{clientRegistrationForm.invalidEmail}")
  private String email;

  /**
   * The age of the user.
   */
  @UserAge
  private Integer age;

  /**
   * The address of the user. The address primary key will be set as a foreign key into the table generated for this
   * class.
   */
  private AddressDto address;

  /**
   * The username of the user
   */
  @NotEmpty(message = "{clientRegistrationForm.usernameRequired}")
  private String username;

  /**
   * The password of the user.
   */
  @NotEmpty(message = "{clientRegistrationForm.passwordRequired}")
  private String password;

  /**
   * The user role for authentication.
   */
  private Set<UserRoleDto> roles;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<UserRoleDto> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRoleDto> roles) {
    this.roles = roles;
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

  public AddressDto getAddress() {
    return address;
  }

  public void setAddress(AddressDto address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", cnp=" + cnp + ", email=" + email + ", age="
        + age + ", address=" + address + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
  }

}
