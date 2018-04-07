package ru.it.controller;

import ru.it.dao.Test;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestController {

    @EJB
    Test test;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String print(@QueryParam("name") String name){
        return test.sayHello(name);
    }
}
