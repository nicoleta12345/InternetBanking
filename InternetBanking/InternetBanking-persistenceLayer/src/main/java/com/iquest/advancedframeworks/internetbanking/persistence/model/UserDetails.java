package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The UserDetails class represents details about the user. Due to @Entity
 * annotation there will be generated a table for this class into a database.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@Table(name = "userDetails")
public class UserDetails {

	/**
	 * The id of the UserDetails objects. It represents the primary key into the
	 * table which will be generated for this class.
	 */
	@Id
	@GeneratedValue
	private int userDetailsId;

	/**
	 * The first name of the user.
	 */
	@NotNull
	private String firstName;

	/**
	 * The last name of the user.
	 */
	@NotNull
	private String lastName;

	/**
	 * The cnp of the user.
	 */
	@NotNull
	private String cnp;

	/**
	 * The age of the user.
	 */
	private Integer age;

	/**
	 * The address of the user. The address primary key will be set as a foreign
	 * key into the table generated for this class.
	 */
	@OneToOne
	@JoinColumn(name = "adress_Id")
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
