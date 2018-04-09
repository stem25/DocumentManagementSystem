package ru.it.controller;

import ru.it.dao.PersonDao;
import ru.it.model.Person;
import ru.it.utils.MapUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;

@Path("/test")
public class TestController {


    @EJB
    private PersonDao personDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person read(@PathParam("id") Long id){
        return personDao.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list(@Context UriInfo uriInfo){
        return personDao.list(MapUtils.getMap(uriInfo));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/t")
    public String test(@Context UriInfo uriInfo){
        Person person = new Person();
        person.setFirstName("test3");
        personDao.create(person);
        return "test";
    }


}
