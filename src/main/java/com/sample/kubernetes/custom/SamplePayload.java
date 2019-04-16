package com.sample.kubernetes.custom;

import lombok.Data;

import javax.validation.Payload;

@Data
public class SamplePayload implements Payload  {

    private String addonUid;
    private String countryCode;

}
