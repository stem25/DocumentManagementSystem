package ru.it.ExceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IndexOutOfBoundsExceptionMapper implements ExceptionMapper<IndexOutOfBoundsException> {

    @Override
    public Response toResponse(IndexOutOfBoundsException e) {
        return Response.status(Response.Status.NOT_FOUND).entity("Nothing found").entity(e).build();
    }

}
