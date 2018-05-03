package ru.it.ExceptionMapper;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import ru.it.Exception.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

    @Override
    public Response toResponse(UnrecognizedPropertyException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect json body"+e.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
