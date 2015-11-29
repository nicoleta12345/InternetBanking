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
public class UserAgeValidator implements ConstraintValidator<UserAge, String> {

  @Override
  public void initialize(UserAge arg0) {
  }

  /**
   * Validates the age field.
   */
  @Override
  public boolean isValid(String ageField, ConstraintValidatorContext constraintContext) {
    int age;

    if (ageField == null) {
      return false;
    }

    try {
      age = Integer.parseInt(ageField);
    }
    catch (NumberFormatException e) {
      return false;
    }

    if (age < 18) {
      return false;
    }

    return true;
  }

}
