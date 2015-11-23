package com.iquest.advancedframeworks.internetbanking.persistence.dao;

/**
 * The GenericDao interface defines the CRUD operations for any type of entity.
 * 
 * @author Nicoleta Barbulescu
 *
 * @param <T>
 */
public interface GenericDao<T> {

  /**
   * Creates a new entry for the given object into the database.
   * 
   * @param t
   *          the object which will be inserted into the database
   */
  void create(T t);

  /**
   * Reads an object of type T with the primary key id, from the database.
   * 
   * @param id
   *          the primary key of the object
   * @return an object of type T identified by its id
   */
  T read(Object id);

  /**
   * Updates an entry into the database with the updated informations given.
   * 
   * @param t
   *          the object which contains the updated details
   * @return the updated object
   */
  T update(T t);

  /**
   * Deletes an object from the database.
   * 
   * @param t
   *          the object of type T corresponding to the entry form the database
   *          which will be deleted
   */
  void delete(T t);

}
