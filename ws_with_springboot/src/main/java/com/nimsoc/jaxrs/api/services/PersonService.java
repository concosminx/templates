package com.nimsoc.jaxrs.api.services;

import com.nimsoc.jaxrs.lib.models.Person;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PersonService {

  Person findPersonById(String id);

  List<Person> findPersons(Pageable pageable);

  Long findPersonsCount();

  Person createPerson(Person transaction);
}
