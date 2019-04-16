package com.sample.kubernetes.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class Customer {
    List<@NotBlank(message="Address must not be blank") String> addresses;
}
