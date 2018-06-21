package com.sample.kubernetes.UnitTest.model;

import com.sample.kubernetes.model.PersonDTO;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PersonDTOTest {

    PersonDTO buildModel() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(2);
        personDTO.setFirstName("Vadivel");
        personDTO.setId("25");
        personDTO.setLastName("Murugan");
        personDTO.setPersonUid("25");
        personDTO.setSex("Male");
        return personDTO;
    }

    @Test
    public void testToString() {
        System.out.println("print model::"+buildModel());
        String expected =  "PersonDTO(" +
                "firstName=Vadivel" +
                ", lastName=Murugan" +
                ", age=2" +
                ", sex=Male" +
                ", personUid=25"  +
                ", id=25"  +
                ')';
        Assert.assertEquals(expected, buildModel().toString());
    }

    @Test
    public void testGetters() {
        PersonDTO personDTO = PersonDTO.builder()
                .age(25)
                .firstName("Vadivel")
                .id("25")
                .lastName("Murugan")
                .personUid("25")
                .sex("Male")
                .build();
        assertThat(personDTO.getAge(),is(25));
        assertThat(personDTO.getFirstName(),is("Vadivel"));
        assertThat(personDTO.getId(),is("25"));
        assertThat(personDTO.getLastName(),is("Murugan"));
        assertThat(personDTO.getPersonUid(),is("25"));
        assertThat(personDTO.getSex(),is("Male"));
    }


}
