package com.sample.kubernetes.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotNull
@Size(min = 4)
@Constraint(
        validatedBy = {}
)
public @interface NotEmpty {
    String message() default "Should not be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
