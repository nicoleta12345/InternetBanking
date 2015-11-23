package com.iquest.advancedframeworks.internetbanking.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iquest.advancedframeworks.internetbanking.persistence.dao.GenericDao;

/**
 * The GenericDaoImpl class implements CRUD operations for any T entity on the
 * database.
 * 
 * @author Nicoleta Barbulescu
 *
 * @param <T>
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

  /**
   * Logger instance used to log information from the GenericDaoImpl class and from classes which extend this class.
   */
  protected static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

  /**
   * EntityManager is used to do operations with the database.
   */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * The class object of the entity at runtime.
   */
  private Class<T> classType;

  /**
   * Parameterized constructor. Sets the runtime class of the entity type.
   */  
  @SuppressWarnings("unchecked")
  public GenericDaoImpl() {
    classType = ((Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);
  }

  @Override
  public void create(T t) {
    entityManager.persist(t);
  }

  @Override
  public T read(Object id) {

    return entityManager.find(classType, id);
  }

  @Override
  public T update(T t) {
    return entityManager.merge(t);
  }

  @Override
  public void delete(T t) {
    // TODO Auto-generated method stub

  }

}
