package com.sample.kubernetes.controller;

import com.sample.kubernetes.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format("Hello, %s!", name));
        greeting.add(linkTo(methodOn(HelloController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
