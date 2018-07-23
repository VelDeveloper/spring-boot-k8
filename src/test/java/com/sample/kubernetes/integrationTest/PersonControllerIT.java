package com.sample.kubernetes.integrationTest;


import com.sample.kubernetes.SpringBootK8Application;
import com.sample.kubernetes.model.PersonDTO;
import com.sample.kubernetes.repository.PersonRepository;
import com.sample.kubernetes.view.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = SpringBootK8Application.class)
public class PersonControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
        personRepository.save(firstPerson());
        personRepository.save(secondPerson());
        //By default baseURI is "http://localhost"
        RestAssured.port = port;
    }

    @Test
    public void getItemsShouldReturnBothItems() {
        when()
                .get("/getAllPerson")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", hasItems("24", "26"))
                .body("personUid", hasItems("1993", "1994"));
    }

    @Test
    public void addItemShouldReturnSavedItem() {

        given()
                .body(createPerson())
                .contentType(ContentType.JSON)
                .when()
                .post("/createPerson")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is("25"))
                .body("personUid", is("1992"));
    }

    @Test
    public void addItemShouldReturnBadRequestWithoutBody() {
        when()
                .post("/createPerson")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void addItemShouldReturnNotSupportedMediaTypeIfNonJSON() {
        given()
                .body(createPerson())
                .when()
                .post("/createPerson")
                .then()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    private Person createPerson() {
        return Person.builder()
                .age(25)
                .firstName("Vadivel Murugan")
                .id("25")
                .lastName("Gopal")
                .personUid("1992")
                .sex("M")
                .build();
    }

    private Person UpdatePerson() {
        return Person.builder()
                .age(25)
                .firstName("Sahana")
                .id("25")
                .lastName("Vadivel Murugan Gopal")
                .personUid("1987")
                .sex("F")
                .build();
    }

    private Person UpdatePersonWithNonExistingId() {
        return Person.builder()
                .age(25)
                .firstName("Sahana")
                .id("255555")
                .lastName("Vadivel Murugan Gopal")
                .personUid("1987")
                .sex("F")
                .build();
    }

    private PersonDTO firstPerson() {
        return PersonDTO.builder()
                .age(25)
                .firstName("Vadivel Murugan")
                .id("24")
                .lastName("Gopal")
                .personUid("1993")
                .sex("M")
                .build();

    }

    private PersonDTO secondPerson() {
        return PersonDTO.builder()
                .age(23)
                .firstName("Vadivel Murugan")
                .id("26")
                .lastName("Gopal")
                .personUid("1994")
                .sex("M")
                .build();

    }


    @Test
    public void updateItemShouldReturnUpdatedItem() {
        // Given an unchecked first item

        given()
                .body(UpdatePerson())
                .contentType(ContentType.JSON)
                .when()
                .put("/updatePerson")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("personUid", is("1987"))
                .body("id", is("25"));
    }

    @Test
    public void updateItemShouldReturnBadRequestWithoutBody() {
        when()
                .put("/updatePerson")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void updateItemShouldReturnNotSupportedMediaTypeIfNonJSON() {
        given()
                .body(UpdatePerson())
                .when()
                .put("/updatePerson")
                .then()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void updateItemShouldBeBadRequestIfNonExistingID() {
        given()
                .body(UpdatePersonWithNonExistingId())
                .contentType(ContentType.JSON)
                .when()
                .put("/updatePerson")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void deleteItemShouldReturn200() {
        when()
                .delete("/deletePerson/{id}", "24")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deleteItemShouldBeBadRequestIfNonExistingID() {
        when()
                .delete("/deletePerson/{id}", "30000")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
