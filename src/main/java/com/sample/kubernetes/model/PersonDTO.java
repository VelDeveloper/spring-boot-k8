package com.sample.kubernetes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by vadivel on 01/10/17.
 */
@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private String personUid;
    private String id;

}
