package ru.it.controller;

import ru.it.dao.DepartmentDao;
import ru.it.dao.OrganizationDao;
import ru.it.dao.PersonDao;
import ru.it.model.Department;
import ru.it.model.Organization;
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

    @EJB
    private OrganizationDao organizationDao;

    @EJB
    private DepartmentDao departmentDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Department read(@PathParam("id") Long id){
        return departmentDao.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> list(@Context UriInfo uriInfo){
        return departmentDao.list(MapUtils.getMap(uriInfo));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/p")
    public String test(@QueryParam("org") Long id){
        Organization org = organizationDao.read(2L);
        Person person = personDao.read(1L);
        Department department = new Department();
        department.setName("test1");
        department.setHead(person);
        department.setOrganization(org);
        departmentDao.create(department);
        return "test";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/o")
    public String testorg(@Context UriInfo uriInfo){
        Organization organization = new Organization();
        organization.setName("testORg");
        organizationDao.create(organization);
        return "test";
    }
}
