package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.ConfirmationMailDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.RegistrationUserEmail;

/**
 * The repository which persist the state of the registration confirmation emails.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class ConfirmationMailDaoImpl extends GenericDaoImpl<RegistrationUserEmail> implements ConfirmationMailDao {

}
