package com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.UserAge;

/**
 * The UserAgeValidator class represents the validation for the UserAge constraint annotation.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class UserAgeValidator implements ConstraintValidator<UserAge, Integer> {

  @Override
  public void initialize(UserAge arg0) {
  }

  /**
   * Validates the age field.
   */
  @Override
  public boolean isValid(Integer ageField, ConstraintValidatorContext constraintContext) {

    if (ageField == null) {
      System.out.println("null");
      return false;
    }

    if (ageField < 18) {
      System.out.println("<18");
      return false;
    }

    System.out.println("true age");
    return true;
  }

}
