package com.sample.kubernetes.model;

import javax.validation.constraints.*;
import java.util.Date;

public class ConstraintsElementBean {
    private Object notNull;
    private String notBlank;
    private String notEmpty;
    private String email;
    private Date future;
    private float digits;

    @NotEmpty
    public String getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(String notEmpty) {
        this.notEmpty = notEmpty;
    }

    @NotNull
    public Object getNotNull() {
        return notNull;
    }

    public void setNotNull(Object notNull) {
        this.notNull = notNull;
    }

    @NotBlank
    public String getNotBlank() {
        return notBlank;
    }

    public void setNotBlank(String notBlank) {
        this.notBlank = notBlank;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Future
    public Date getFuture() {
        return future;
    }

    public void setFuture(Date future) {
        this.future = future;
    }

    @Digits(integer = 10,fraction = 0)
    public float getDigits() {
        return digits;
    }

    public void setDigits(float digits) {
        this.digits = digits;
    }

}
