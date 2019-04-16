package com.sample.kubernetes.custom;

import com.sample.kubernetes.model.UserInfo;
import lombok.Data;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class CheckInformationChunks {

    @Autowired
    ValidatorFactory factory;

    public static void main(String[] args) {
        CheckInformationChunks checkInformationChunks = new CheckInformationChunks();
        checkInformationChunks.invokeValidate();

    }

    public List<Map<String,Object>> invokeValidate() {
        SamplePayload samplePayload = new SamplePayload();
        samplePayload.setAddonUid("sampleAddonUid");
        samplePayload.setCountryCode("US");
        if (factory == null) {
            factory = Validation.byProvider(HibernateValidator.class)
        .configure().constraintValidatorPayload(samplePayload).buildValidatorFactory();
        }
        Validator validator = factory.getValidator();
        UserInfo userInfo = new UserInfo();

        List<Map<String,Object>> informationChunks = new ArrayList<>();
        informationChunks.add(getChunck("1"));
        informationChunks.add(getChunck("2"));
        userInfo.setInformationChunks(informationChunks);
        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
        System.out.println("violations::"+violations);
        return informationChunks;

    }

    private Map<String,Object> getChunck(String chunmNumber) {
        Map<String, Object> chunck = new HashMap<>();
        chunck.put("service.passportNumber","D12345678"+chunmNumber);
        chunck.put("service.emergencyContactFirstName", "John"+chunmNumber);
        chunck.put("service.emergencyContactSurname", "Smith"+chunmNumber);
        Phone phone = new Phone();
        phone.setPhone_number("0123456789"+chunmNumber);
        NumberInfo numberInfo = new NumberInfo();
        numberInfo.setCountryCode("GB");
        numberInfo.setE164Format("+44123456789");
        numberInfo.setNumberRegion("GB");
        numberInfo.setNumberType("GB");
        chunck.put("service.phoneNumber",numberInfo);
        chunck.put("service.email","a@b.com");
        return chunck;
    }
    @Data
    public class Phone {
        private String phone_number;
        private NumberInfo numberInfo;
    }

    @Data
    public class NumberInfo {
        private String countryCode;
        private String numberRegion;
        private String numberType;
        private String e164Format;
    }
}
