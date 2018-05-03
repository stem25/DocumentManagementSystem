package ru.it.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.it.model.Person;
import ru.it.service.PersonService;
import ru.it.utils.MapUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/entity/Person")
public class PersonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @EJB
    private PersonService service;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person read(@PathParam("id") Long id){
        return service.read(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list(@Context UriInfo uriInfo){
        return service.list(MapUtils.getMap(uriInfo));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    public Integer count(@Context UriInfo uriInfo){
        return service.count(MapUtils.getMap(uriInfo));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person create(String json) throws IOException {
        Person entity = objectMapper.readValue(json, Person.class);
        return service.create(entity);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person update(@PathParam("id") Long id, String json) throws IOException {
        Person entity = objectMapper.readValue(json, Person.class);
        entity.setId(id);
        service.update(entity);
        return entity;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        service.remove(id);
    }
}
