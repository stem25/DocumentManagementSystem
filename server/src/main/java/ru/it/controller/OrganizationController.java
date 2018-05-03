package ru.it.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import ru.it.model.Organization;
import ru.it.model.Person;
import ru.it.service.OrganizationService;
import ru.it.service.PersonService;
import ru.it.utils.MapUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/entity/Organization")
public class OrganizationController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @EJB
    private OrganizationService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Organization read(@PathParam("id") Long id){
        return service.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> list(@Context UriInfo uriInfo){
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
    public Organization create(String json) throws IOException {
        Organization entity = objectMapper.readValue(json, Organization.class);
        return service.create(entity);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Organization update(@PathParam("id") Long id, String json) throws IOException {
        Organization entity = objectMapper.readValue(json, Organization.class);
        return service.update(entity);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        service.remove(id);
    }
}
