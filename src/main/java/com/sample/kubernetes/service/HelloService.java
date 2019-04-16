package com.sample.kubernetes.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {


  public String sayHello() {
    return "Hello.Welcome to our site!!!!";
  }
}
