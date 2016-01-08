package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The UserRole class represents the role of a user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "ROLES")
public class UserRole {

  /**
   * The id of the role. It represents the primary key for the table which will be generated.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The role of the user.
   */
  @Column(name = "ROLE", nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "UserRole [id=" + id + ", role=" + role + "]";
  }

}
