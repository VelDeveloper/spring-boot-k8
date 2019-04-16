package com.sample.kubernetes.custom;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;

public class URLValidator implements ConstraintValidator<URL,String> {

    private String protocol;
    private String host;
    private int port;

    @Override
    public void initialize(URL url) {
        this.protocol = url.protocol();
        this.host = url.host();
        this.port = url.port();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        java.net.URL url;
        try {
            url = new java.net.URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        if(StringUtils.isNotBlank(protocol) && !url.getProtocol().equals(protocol))
            return false;
        if (port != -1 && url.getPort() != port)
            return false;
        return true;
    }
}
