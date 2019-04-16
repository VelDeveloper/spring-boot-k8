package com.sample.kubernetes.UnitTest;

import com.sample.kubernetes.model.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;
import static org.junit.Assert.assertEquals;

public class ValidationTest {
    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.byDefaultProvider().configure().buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenEmptyAddress_thenValidationFails() {
        Customer customer = new Customer();

        customer.setAddresses(Collections.singletonList(" "));
        Set<ConstraintViolation<Customer>> violations =
                validator.validate(customer);

        assertEquals(1, violations.size());
        assertEquals("Address must not be blank",
                violations.iterator().next().getMessage());
    }
}
