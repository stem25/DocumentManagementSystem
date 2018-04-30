package ru.it.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.it.model.Order;
import ru.it.service.OrderService;
import ru.it.utils.MapUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/entity/Order")
public class OrderController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @EJB
    private OrderService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Order read(@PathParam("id") Long id){
        return service.read(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> list(@Context UriInfo uriInfo){
        return service.list(MapUtils.getMap(uriInfo));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Order create(String json) throws IOException {
        Order entity = objectMapper.readValue(json, Order.class);
        return service.create(entity);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Order update(@PathParam("id") Long id, String json) throws IOException {
        Order entity = objectMapper.readValue(json, Order.class);
        entity.setId(id);
        service.update(entity);
        return entity;
    }


}
