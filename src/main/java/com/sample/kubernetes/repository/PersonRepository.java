package com.sample.kubernetes.repository;

import com.sample.kubernetes.model.PersonDTO;
import com.sample.kubernetes.view.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vadivel on 01/10/17.
 */
@Repository
public interface PersonRepository extends MongoRepository<PersonDTO,String> {

}
