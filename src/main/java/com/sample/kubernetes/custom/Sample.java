package com.sample.kubernetes.custom;

import java.util.Optional;

public class Sample {
  public static void main(String[] args) {
    int region = 49;
    String regionStr = "+"+region;
    System.out.println(regionStr);

    Sample sample = new Sample();
    sample.validate();
    sample.isValidate();
  }

  private void isValidate() {
    Optional.of(getStatus(false)).ifPresent(val -> System.out.println("val::"+val));
  }
  private void validate() {
      boolean isValid = true;
      Boolean result = Optional.of(isValid)
              .filter(this::getStatus)
              .filter(this::getSecondStatus)
              .orElse(false);
    System.out.println("result::"+result);
  }
  private boolean getStatus(boolean status){
      return status;
  }
    private boolean getSecondStatus(boolean status){
        return status;
    }
}
