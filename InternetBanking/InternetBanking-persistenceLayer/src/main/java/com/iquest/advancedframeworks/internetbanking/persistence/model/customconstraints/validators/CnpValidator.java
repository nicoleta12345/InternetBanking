package com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.Cnp;

/**
 * The CnpValidator class represents the validation for the Cnp constraint annotation.
 * 
 * @author Nicoleta Barbulescu
 *
 */
public class CnpValidator implements ConstraintValidator<Cnp, String> {

  @Override
  public void initialize(Cnp arg0) {
  }

  /**
   * Validate the cnp field.
   */
  @Override
  public boolean isValid(String cnpField, ConstraintValidatorContext constraintContext) {
    String regex = "[0-9]+";

    if (cnpField == null) {
      return true;
    }

    if (cnpField.matches(regex) == false) {
      return false;
    }

    if (cnpField.length() != 13) {
      return false;
    }

    char firstDigit = cnpField.charAt(0);

    if ((firstDigit != '0') || (firstDigit != '1')) {
      return false;
    }

    return true;
  }

}
