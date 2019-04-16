package com.sample.kubernetes.custom;

import com.sample.kubernetes.model.ConstraintsElementBean;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import javax.validation.constraints.NotEmpty;
import java.beans.Introspector;
import java.util.*;

public class ValidateUserInfoValidator implements ConstraintValidator<ValidateUserInfo, List<Map<String, Object>>> {

    @Override
    public void initialize(ValidateUserInfo constraintAnnotation) {
        System.out.println(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Map<String, Object>> mapList, ConstraintValidatorContext constraintValidatorContext) {
        if(mapList == null || CollectionUtils.isEmpty(mapList)) {
            //null collection cannot be validated
            return false;
        }
        boolean isValid = true;
        System.out.println("Entered::"+constraintValidatorContext);
        SamplePayload samplePayload = constraintValidatorContext
                .unwrap( HibernateConstraintValidatorContext.class )
                .getConstraintValidatorPayload( SamplePayload.class );
        System.out.println("sample payload::"+samplePayload);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<?>> violations = new HashSet<ConstraintViolation<?>>();

        String propertyName = NotEmpty.class.getSimpleName();
        propertyName = Introspector.decapitalize(propertyName);
        violations.addAll(validator.validateValue(ConstraintsElementBean.class, propertyName, "sample"));
        if(!violations.isEmpty()) {
            System.out.println("violations::"+violations);
            isValid = false;
        } else {
            System.out.println("violations not empty::"+violations);
        }
        return isValid;
    }
}
