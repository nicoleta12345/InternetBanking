package com.iquest.advancedframeworks.internetbanking.persistence.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The CreditAccount class represents a specific type of account.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Entity
@DiscriminatorValue("CreditAccount")
public class CreditAccount extends Account {

}
