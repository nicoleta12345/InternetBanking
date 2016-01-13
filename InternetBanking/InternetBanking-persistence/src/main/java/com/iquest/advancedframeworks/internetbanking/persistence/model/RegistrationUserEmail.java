package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Keeps the state of the confirmation mail of registration.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
public class RegistrationUserEmail {

  /**
   * The id of the entity.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The status of the confirmation email.
   */
  @Column(name = "STATUS")
  private String status;

  /**
   * The correlationId used to make the request to a jms queue to send the confirmation email.
   */
  @Column(name = "CORRELATION_ID")
  private String correlationId;

  public int getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }

}
