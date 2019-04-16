package com.sample.kubernetes.custom;

import org.springframework.http.MediaType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class TestDate {
  public static void main(String[] args) {
    System.out.println("MediaType::"+ MediaType.APPLICATION_JSON.toString());
    System.out.println("IsDivisibleBythree::"+callthis());
      Instant instant = Instant.now() ;
    System.out.println(instant);
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
          dateFormat.setLenient(false);
          System.out.println("success");
          try {
              dateFormat.parse("2018-10-08");
          } catch (ParseException pe) {
              System.out.println("Exception");
          }
  }

  public static boolean callthis(){
      double number = 81;
      for(; number > 1; number /=3);
      return number == 1;
  }
}
