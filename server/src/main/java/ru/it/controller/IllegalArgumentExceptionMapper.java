package ru.it.controller;

import org.glassfish.jersey.internal.Errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException e) {

        return Response.status(Response.Status.BAD_GATEWAY).entity("NOPE").build();
    }
}
