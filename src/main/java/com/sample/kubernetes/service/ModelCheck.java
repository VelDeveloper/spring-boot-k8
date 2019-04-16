package com.sample.kubernetes.service;

import com.sample.kubernetes.model.AnotherCar;
import com.sample.kubernetes.model.AnotherPerson;
import com.sample.kubernetes.model.Car;
import com.sample.kubernetes.model.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelCheck {

  public static void main(String[] args) {
    ModelMapper modelMapper = new ModelMapper();

//    modelMapper.addConverter((MappingContext<Car, AnotherCar> context) -> {
//        Person person = (Person) context.getParent().getParent().getSource();
//       System.out.println("Person::"+person);
//        context.getDestination().setPersonName(person.getName());
//        context.getDestination().setType(context.getSource().getType());
//        return context.getDestination();
//      });
    modelMapper.addMappings(new PropertyMap<Person, AnotherPerson>() {
      @Override
      protected void configure() {
        List<AnotherCar> cars = new ArrayList<>();
          for (Car car : source.getCars()) {
            AnotherCar anotherCar = AnotherCar.builder()
                .personName(source.getName())
                .type(car.getType()).build();
            cars.add(anotherCar);
          }
      }
    });
    Person person = Person.builder()
        .name("joe").build();
    Car car1 = Car.builder()
        .type("Honda").build();
    Car car2 = Car.builder()
        .type("Toyota").build();
    person.setCars(Arrays.asList(car1,car2));
    AnotherPerson anotherPerson = modelMapper.map(person, AnotherPerson.class);
    System.out.println("Another person::"+anotherPerson);
  }
}
