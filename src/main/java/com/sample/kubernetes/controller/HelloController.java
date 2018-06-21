package com.sample.kubernetes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vadivel on 01/11/17.
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/sayHello")
    public String sayHello() {
        LOGGER.info("HelloController sayHello using info");
        LOGGER.debug("HelloController sayHello using debug");
        LOGGER.trace("HelloController sayHello using trace");
        LOGGER.warn("HelloController sayHello using warn");
        return "Hello.Welcome to our site!!!!";
    }

}
