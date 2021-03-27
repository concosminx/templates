package com.nimsoc.jaxrs.api.services;

import com.nimsoc.jaxrs.lib.models.Person;

import java.util.List;

public interface PersonService {

  Person findPersonById(String id);

  List<Person> findPersons();

  Long findPersonsCount();

  Person createPerson(Person transaction);
}
