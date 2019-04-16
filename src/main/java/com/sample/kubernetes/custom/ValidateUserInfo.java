package com.sample.kubernetes.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateUserInfoValidator.class)
public @interface ValidateUserInfo {
    String message() default "Should not be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
