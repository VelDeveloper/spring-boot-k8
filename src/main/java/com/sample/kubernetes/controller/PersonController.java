package com.sample.kubernetes.controller;

import com.sample.kubernetes.model.PersonDTO;
import com.sample.kubernetes.repository.PersonRepository;
import com.sample.kubernetes.view.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by vadivel on 01/10/17.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/getAllPerson")
    public List<PersonDTO> getPersonList(){
        return personRepository.findAll();
    }

    @GetMapping(value = "/getAllPersonHal")
    public ResponseEntity<List<Person>> getPersonListHal(){
        List<PersonDTO> allPersons = personRepository.findAll();
        List<Person> personList = new ArrayList<>();
        for (PersonDTO personDTO : allPersons) {
            Person person = new Person();
            modelMapper.map(personDTO,person);
            person.add(linkTo(methodOn(PersonController.class).getPersonById(personDTO.getId())).withSelfRel());
            person.add(linkTo(methodOn(PersonController.class).getPersonById(personDTO.getId())).withRel("persons"));
            person.add(linkTo(methodOn(PersonController.class)
                    .getPersonById(personDTO.getId()))
                    .withRel("urn:persons"));
            personList.add(person);
        }
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping(value = "/getPersonById/{id}")
    public PersonDTO getPersonById(@PathVariable("id") String id){
        return personRepository.findOne(id);
    }

    @PostMapping(value = "/createPerson")
    public Person createPerson(@RequestBody Person person) {
        PersonDTO personDTO = new PersonDTO();
        modelMapper.map(person,personDTO);
        PersonDTO resultPerson = personRepository.insert(personDTO);
        Person personResponse = new Person();
        modelMapper.map(resultPerson,personResponse);
        return personResponse;
    }

    @PutMapping(value = "/updatePerson")
    public Person updatePerson(@RequestBody Person person) {
        PersonDTO personDTO = new PersonDTO();
        modelMapper.map(person,personDTO);
        PersonDTO resultPerson = personRepository.save(personDTO);
        Person personResponse = new Person();
        modelMapper.map(resultPerson,personResponse);
        return personResponse;
    }

    @DeleteMapping(value = "/deletePerson/{id}")
    public void deletePerson(@PathVariable("id") String id) {
        personRepository.delete(id);
    }

}
