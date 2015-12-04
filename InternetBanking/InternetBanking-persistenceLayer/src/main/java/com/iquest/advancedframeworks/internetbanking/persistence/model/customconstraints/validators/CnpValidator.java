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
      System.out.println("null");
      return true;
    }

    if (cnpField.matches(regex) == false) {
      System.out.println("regex");
      return false;
    }

    if (cnpField.length() != 13) {
      System.out.println("!13");
      return false;
    }

    char firstDigit = cnpField.charAt(0);
    
    if ((firstDigit != '1') && (firstDigit != '2')) {
      System.out.println(cnpField.charAt(0));
      System.out.println("firstDigit");
      return false;
    }

    System.out.println("true constraint");
    return true;
  }

}
