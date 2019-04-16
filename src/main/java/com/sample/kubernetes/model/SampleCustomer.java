package com.sample.kubernetes.model;

import com.sample.kubernetes.custom.NotEmpty;
import com.sample.kubernetes.custom.URL;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SampleCustomer {

    @NotNull
    @Size(min = 4, max = 50)
    private String firstName;
    @NotEmpty
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dob;
    @URL
    private String resourceURL;
    @URL(protocol = "ftp", port = 21)
    private String ftpURL;
    @URL(protocol = "https", port = 22)
    private String httpsURL;
}
