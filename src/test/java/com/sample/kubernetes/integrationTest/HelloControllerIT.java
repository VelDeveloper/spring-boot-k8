package com.sample.kubernetes.integrationTest;

import com.sample.kubernetes.SpringBootK8Application;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = SpringBootK8Application.class)
public class HelloControllerIT {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        //By default baseURI is "http://localhost"
        RestAssured.port = port;
    }

    @Test
    public void getSayHello() {
        when()
                .get("/sayHello")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is("Hello.Welcome to our site!!!!"));
    }

}
