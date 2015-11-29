package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.UserRoleDao;
import com.iquest.advancedframeworks.internetbanking.persistence.model.UserRole;

/**
 * The UserRoleDaoImpl class implements UserRoleDao interface and extends the abstract class GenericDaoImpl taking
 * benefits of its methods and adding more specific ones.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao {

}
