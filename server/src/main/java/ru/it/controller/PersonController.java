package ru.it.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.it.dao.OrganizationDao;
import ru.it.dao.PersonDao;
import ru.it.model.Organization;
import ru.it.model.Person;
import ru.it.service.PersonService;
import ru.it.utils.MapUtils;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/entity/Person")
public class PersonController {

    ObjectMapper objectMapper = new ObjectMapper();

    @EJB
    private PersonService personService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person read(@PathParam("id") Long id){
        return personService.read(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list(@Context UriInfo uriInfo){
        return personService.list(MapUtils.getMap(uriInfo));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person create(String json) throws IOException {
        Person person1 = objectMapper.readValue(json, Person.class);
        return personService.create(person1);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person update(@PathParam("id") Long id, String json) throws IOException {
        Person person1 = objectMapper.readValue(json, Person.class);
        person1.setId(id);
        personService.update(person1);
        return person1;
    }


}
