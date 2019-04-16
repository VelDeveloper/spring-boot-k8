package com.sample.kubernetes.custom;

import com.sample.kubernetes.model.SampleCustomer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CheckValidation {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static void main(String[] args) {

        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("velu");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelgmurugan@gmail.com");
        sampleCustomer.setLastName("lastName");
        sampleCustomer.setPhoneNumber("phoneNumber");
        Set<ConstraintViolation<SampleCustomer>> violations = validator.validate(sampleCustomer);
        if(violations.size() > 0) {
            System.out.println("Error");
            System.out.println(violations);
        } else {
            System.out.println("passed");
        }
    }
}
