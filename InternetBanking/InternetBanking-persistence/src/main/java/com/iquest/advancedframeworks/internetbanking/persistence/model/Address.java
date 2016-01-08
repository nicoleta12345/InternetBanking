package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Address class represents a address of an user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "ADDRESS")
public class Address {

  /**
   * The id of the address. This represents the primary key for the table created into the database.
   */
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  /**
   * The town for the address.
   */
  @Column(name = "TOWN")
  private String town;

  /**
   * The name of the street
   */
  @Column(name = "STREET_NAME")
  private String streetName;

  /**
   * The number of the street.
   */
  @Column(name = "STREET_NUMBER")
  private int streetNumber;

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public int getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(int streetNumber) {
    this.streetNumber = streetNumber;
  }

  @Override
  public String toString() {
    return "Address [id=" + id + ", town=" + town + ", streetName=" + streetName + ", streetNumber=" + streetNumber
        + "]";
  }

}
