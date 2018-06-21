package com.sample.kubernetes.controller;

import com.sample.kubernetes.model.PersonDTO;
import com.sample.kubernetes.repository.PersonRepository;
import com.sample.kubernetes.view.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
