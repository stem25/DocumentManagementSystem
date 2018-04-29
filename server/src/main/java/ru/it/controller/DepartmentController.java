package ru.it.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.it.model.Department;
import ru.it.model.Organization;
import ru.it.service.DepartmentService;
import ru.it.service.OrganizationService;
import ru.it.utils.MapUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/entity/Department")
public class DepartmentController {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @EJB
    private DepartmentService service;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Department read(@PathParam("id") Long id){
        return service.read(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> list(@Context UriInfo uriInfo){
        return service.list(MapUtils.getMap(uriInfo));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Department create(String json) throws IOException {
        Department entity = objectMapper.readValue(json, Department.class);
        return service.create(entity);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Department update(@PathParam("id") Long id, String json) throws IOException {
        Department entity = objectMapper.readValue(json, Department.class);
        entity.setId(id);
        return service.update(entity);
    }
}
