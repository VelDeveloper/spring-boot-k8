package com.sample.kubernetes.UnitTest;

import com.sample.kubernetes.controller.PersonController;
import com.sample.kubernetes.model.PersonDTO;
import com.sample.kubernetes.repository.PersonRepository;
import com.sample.kubernetes.view.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PersonDTOControllerTest {

    @InjectMocks
    public PersonController personController;

    @Mock
    public PersonRepository personRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPersonById() {
        PersonDTO personDTO = createPersonDTO("25");
        when(personRepository.findOne("25")).thenReturn(personDTO);
        PersonDTO resultResponse = personController.getPersonById("25");
        verify(personRepository).findOne("25");
        assertThat(resultResponse.getId(),is("25"));
    }

    @Test
    public void testGetPersonList() {
        PersonDTO personDTO = createPersonDTO("25");
        PersonDTO personDTO2 = createPersonDTO("26");
        PersonDTO personDTO3 = createPersonDTO("27");
        List<PersonDTO> personDTOList = Arrays.asList(personDTO, personDTO2, personDTO3);
        when(personRepository.findAll()).thenReturn(personDTOList);
        List<PersonDTO> resultResponse = personController.getPersonList();
        verify(personRepository).findAll();
        assertEquals(resultResponse.size(), 3);
    }

    @Test
    public void testCreatePerson() {
        PersonDTO personDTO = createPersonDTO("25");
        Person person = createPerson("25");
        when(personRepository.insert(personDTO)).thenReturn(personDTO);
        Person resultResponse = personController.createPerson(person);
        verify(personRepository).insert(personDTO);
        //assertThat(resultResponse.getId(),is("25"));
    }

    @Test
    public void testUpdatePerson() {
        PersonDTO personDTO = createPersonDTO("25");
        Person person = createPerson("25");
        when(personRepository.save(personDTO)).thenReturn(personDTO);
        Person resultResponse = personController.updatePerson(person);
        verify(personRepository).save(personDTO);
        assertThat(resultResponse.getId(),is("25"));
    }

    @Test
    public void testDeletePerson() {
        PersonDTO personDTO = createPersonDTO("25");
        doNothing().when(personRepository).delete(personDTO.getId());
        personController.deletePerson(personDTO.getId());
        verify(personRepository).delete(personDTO.getId());
    }

    public Person createPerson(String id) {
        return Person.builder()
                .age(25)
                .personUid("personUid")
                .firstName("Vadivel")
                .id(id)
                .lastName("Murugan")
                .sex("Male")
                .build();
    }

    public PersonDTO createPersonDTO(String id) {
        return PersonDTO.builder()
                .age(25)
                .personUid("personUid")
                .firstName("Vadivel")
                .id(id)
                .lastName("Murugan")
                .sex("Male")
                .build();
    }
}
