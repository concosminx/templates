package com.nimsoc.jaxrs.api.rest.v1.resources;

import com.nimsoc.jaxrs.api.services.PersonService;
import com.nimsoc.jaxrs.lib.models.CountResponse;
import com.nimsoc.jaxrs.lib.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/persons")
public class PersonResource {

  private final PersonService personService;

  @Autowired
  public PersonResource(PersonService personService) {
    this.personService = personService;
  }

  @RequestMapping()
  public ResponseEntity getPersons(Pageable pageable) {

    List<Person> persons = personService.findPersons(pageable);
    Long personsCount = personService.findPersonsCount();

    return ResponseEntity.ok().header("X-Total-Count", personsCount.toString()).body(persons);
  }

  @RequestMapping("/count")
  public ResponseEntity getPersonsCount() {

    Long personsCount = personService.findPersonsCount();

    CountResponse countResponse = new CountResponse();
    countResponse.setCount(personsCount);

    return ResponseEntity.ok(countResponse);
  }

  @RequestMapping("/{id}")
  public ResponseEntity getPerson(@PathVariable("id") String id) {

    Person person = personService.findPersonById(id);

    return ResponseEntity.ok(person);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity createPerson(@RequestBody Person newPerson) {

    Person person = personService.createPerson(newPerson);

    return ResponseEntity.ok(person);
  }
}
