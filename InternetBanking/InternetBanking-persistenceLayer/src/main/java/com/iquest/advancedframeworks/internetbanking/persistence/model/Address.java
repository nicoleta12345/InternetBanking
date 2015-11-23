package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Address class represents addresses mapped into objects. Into the database
 * will be created a table for this class due to @Entity annotation.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "address")
public class Address {

	/**
	 * The id of the address. This represents the primary key for the table
	 * created into the database.
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * The town for the address.
	 */
	private String town;

	/**
	 * The name of the street
	 */
	private String streetName;

	/**
	 * The number of the street.
	 */
	private int streetNumber;

	/**
	 * The postal code of the address.
	 */
	private Long postalCode;

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

	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}

}
