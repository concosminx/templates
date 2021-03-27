package com.nimsoc.jaxrs.api.services.impl;

import com.nimsoc.jaxrs.api.mapper.PersonMapper;
import com.nimsoc.jaxrs.api.models.PersonRepository;
import com.nimsoc.jaxrs.api.models.db.PersonEntity;
import com.nimsoc.jaxrs.api.services.PersonService;
import com.nimsoc.jaxrs.api.services.exceptions.EmptyPayloadException;
import com.nimsoc.jaxrs.api.services.exceptions.ResourceNotFoundException;
import com.nimsoc.jaxrs.lib.models.Gender;
import com.nimsoc.jaxrs.lib.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Person findPersonById(String id) {
    PersonEntity personEntity = personRepository.findById(id).get();

    if (personEntity == null) {
      throw new ResourceNotFoundException(Person.class.getSimpleName(), id);
    }

    return PersonMapper.toPerson(personEntity);
  }

  @Override
  public List<Person> findPersons() {

    List<PersonEntity> personEntities = personRepository.findAll();
    
    if (personEntities.isEmpty()) {
      PersonEntity pe = new PersonEntity();
      pe.setName("Bogus");
      pe.setAge(20);
      pe.setGender(Gender.M);
      pe.setCreatedAt(new java.util.Date());
      pe.setId("N/A");
      
      personEntities.add(pe);
    }

    return personEntities.stream().map(PersonMapper::toPerson).collect(Collectors.toList());
  }

  @Override
  public Long findPersonsCount() {

    return personRepository.count();
  }

  @Override
  public Person createPerson(Person person) {

    if (person == null) {
      throw new EmptyPayloadException(Person.class.getSimpleName());
    }

    PersonEntity personEntity = PersonMapper.toEntity(person);
    personEntity.setId(null);

    personRepository.save(personEntity);

    return PersonMapper.toPerson(personEntity);
  }
}
