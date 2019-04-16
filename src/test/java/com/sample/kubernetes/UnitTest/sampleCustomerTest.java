package com.sample.kubernetes.UnitTest;

import com.sample.kubernetes.model.SampleCustomer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Ignore
public class sampleCustomerTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Set<ConstraintViolation<SampleCustomer>> violations;

    @BeforeClass
    public static void init() throws ParseException {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldRaiseNoConstraintViolation() {
        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("velu");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelgmurugan@gmail.com");
        sampleCustomer.setLastName("lastName");
        sampleCustomer.setPhoneNumber("phoneNumber");
         violations = validator.validate(sampleCustomer);
    }

    @Test
    public void shouldRaiseConstraintViolation() {
        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("vel");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelgmurugan@gmail.com");
        sampleCustomer.setLastName("lastName");
        sampleCustomer.setPhoneNumber("phoneNumber");
        violations = validator.validate(sampleCustomer);
        assertEquals(1,violations.size());
    }

    @Test
    public void shouldRaiseNoConstraintViolationNonempty() {
        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("vel");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelmurugan@gmail.com");
        sampleCustomer.setLastName("lastName");
        sampleCustomer.setPhoneNumber("phoneNumber");
        violations = validator.validate(sampleCustomer);
        assertEquals(1,violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationNonempty() {
        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("vel");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelmurugan@gmail.com");
        sampleCustomer.setLastName("las");
        sampleCustomer.setPhoneNumber("phoneNumber");
        violations = validator.validate(sampleCustomer);
    }

    @Test
    public void shouldRaiseNoConstraintViolationURL() {
        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("vel");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelmurugan@gmail.com");
        sampleCustomer.setLastName("las");
        sampleCustomer.setPhoneNumber("phoneNumber");
        sampleCustomer.setFtpURL("ftp://sample.com");
        sampleCustomer.setHttpsURL("https://sample.com");
        sampleCustomer.setResourceURL("http://sample.com");
        violations = validator.validate(sampleCustomer);
    }

    @Test
    public void shouldRaiseConstraintViolationURL() {
        SampleCustomer sampleCustomer = new SampleCustomer();
        sampleCustomer.setFirstName("vel");
        sampleCustomer.setDob("dob");
        sampleCustomer.setEmail("vadivelmurugan@gmail.com");
        sampleCustomer.setLastName("las");
        sampleCustomer.setPhoneNumber("phoneNumber");
        sampleCustomer.setFtpURL("ftp://sample.com");
        sampleCustomer.setHttpsURL("http://sample.com");
        sampleCustomer.setResourceURL("http://sample.com");
        violations = validator.validate(sampleCustomer);
        System.out.println(violations);
        assertEquals(4,violations.size());
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }
}
