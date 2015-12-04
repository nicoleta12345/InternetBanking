package com.iquest.advancedframeworks.internetbanking.services.dto;

/**
 * The AddressDto class represents the address of user.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class AddressDto {

  /**
   * The name of the town.
   */
  private String town;

  /**
   * The name of the street.
   */
  private String streetName;

  /**
   * The number of the street.
   */
  private Integer streetNumber;

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
    return "AddressDto [town=" + town + ", streetName=" + streetName + ", streetNumber=" + streetNumber + "]";
  }

}
