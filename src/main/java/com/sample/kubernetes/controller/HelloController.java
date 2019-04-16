package com.sample.kubernetes.controller;

import com.sample.kubernetes.exception.InvalidIdException;
import com.sample.kubernetes.model.Greeting;
import com.sample.kubernetes.model.Hello;
import com.sample.kubernetes.service.HelloService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by vadivel on 01/11/17.
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/sayHello")
    public String sayHello() {
        LOGGER.info("HelloController sayHello using info");
        LOGGER.debug("HelloController sayHello using debug");
        LOGGER.trace("HelloController sayHello using trace");
        LOGGER.warn("HelloController sayHello using warn");
        return helloService.sayHello();
    }

    @GetMapping(value = "/sayHello/json")
    public Hello sayHelloPojo() {
        Hello hello = Hello.builder()
            .description("Hello how are you.")
            .title("Say hello")
            .build();
        return hello;
    }

    @GetMapping(value = "/sayHello/json/{id}")
    public Hello sayHelloPojoWithId(@PathVariable("id") String id) {
        if(StringUtils.isBlank(id)) {
            throw new InvalidIdException("Hello ID shouldn't be null.");
        }
        Hello hello = Hello.builder()
            .description("Hello how are you."+id)
            .title("Say hello")
            .build();
        return hello;
    }

    @GetMapping(value = "/sayHello/json/list")
    public List<Hello> sayHelloPojoList(@RequestParam String param1,
        @RequestParam String param2) {
        System.out.println("param1 :"+param1+":param2:"+param2);
        Hello hello = Hello.builder()
            .description("Hello how are you."+param1)
            .title("Say hello")
            .build();
        Hello hello1 = Hello.builder()
            .description("Hello second time."+param2)
            .title("Hello title")
            .build();
        List<Hello> helloList = new ArrayList<>();
        helloList.add(hello);
        helloList.add(hello1);
        return helloList;
    }

    @GetMapping("/greeting")
    public ResponseEntity<Resource<Greeting>> greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        Greeting greeting = new Greeting(String.format("Hello, %s!", name));
        Resource<Greeting> greetingResource = new Resource<>(greeting);

        greetingResource.add(linkTo(methodOn(HelloController.class).greeting(name)).withSelfRel());
//        Link link = new Link("http://localhost:8080/something").withRel("ACTIONS").expand();
//        SuperLink superLink = new SuperLink(link, MediaTypes.HAL_JSON_VALUE,"GET","/v1/books","v1/describedBy");
//        greeting.add(superLink);
        return new ResponseEntity<>(greetingResource, HttpStatus.OK);
    }
}
