package com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.iquest.advancedframeworks.internetbanking.persistence.model.customconstraints.validators.CnpValidator;

/**
 * The Cnp constraint annotation is used to validate cnp fields.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CnpValidator.class)
@Documented
public @interface Cnp {

  String message() default "{default message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
