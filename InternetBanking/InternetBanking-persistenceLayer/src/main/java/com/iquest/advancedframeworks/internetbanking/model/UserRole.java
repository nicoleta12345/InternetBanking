package com.iquest.advancedframeworks.internetbanking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserRole {

  /**
   * The id of the role. It represents the primary key for the table which will
   * be generated.
   */
  @Id
  @GeneratedValue
  private int id;
  
  private String role;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserRole [id=" + id + ", role=" + role + "]";
  }
  
}
