package com.nimsoc.jaxrs.api.rest.v1.resources;

import com.nimsoc.jaxrs.api.services.PersonService;
import com.nimsoc.jaxrs.lib.models.CountResponse;
import com.nimsoc.jaxrs.lib.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    public Response getPersons() {

        List<Person> persons = personService.findPersons();
        Long personsCount = personService.findPersonsCount();

        return Response.ok().header("X-Total-Count", personsCount.toString()).entity(persons).build();
    }

    @GET
    @Path("/count")
    public Response getPersonsCount() {

        Long personsCount = personService.findPersonsCount();

        CountResponse countResponse = new CountResponse();
        countResponse.setCount(personsCount);

        return Response.ok(countResponse).build();
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") String id) {

        Person person = personService.findPersonById(id);

        return Response.ok(person).build();
    }

    @POST
    public Response createPerson(Person newPerson) {

        Person person = personService.createPerson(newPerson);

        return Response.ok(person).build();
    }
}
