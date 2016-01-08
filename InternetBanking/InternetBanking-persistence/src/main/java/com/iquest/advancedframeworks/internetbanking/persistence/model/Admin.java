package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The Admin class represents a specific type of user with more administrative rights.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

}
