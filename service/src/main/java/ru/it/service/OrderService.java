package ru.it.service;

import ru.it.dao.OrderDao;
import ru.it.model.Order;
import ru.it.model.Order;
import ru.it.model.OrderStatus;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class OrderService implements Crud<Order> {
    @EJB
    private OrderDao dao;

    @EJB
    private StatusService statusService;

    @Override
    public Order create(Order entity) {
        entity.setStatus(OrderStatus.PREPARING);
        Long id = dao.create(entity);
        return dao.read(id);
    }

    @Override
    public Order read(Long id) {
        return dao.read(id);
    }

    @Override
    public List<Order> list(Map<String, String> filter) {
        return dao.list(filter);
    }

    @Override
    public Integer count(Map<String, String> filter) {
        return dao.count(filter);
    }

    @Override
    public Order update(Order entity) {
        Order order = dao.read(entity.getId());
        List<OrderStatus> statuses = statusService.getAvailableStatuses(order.getStatus());
        if(!statuses.contains(entity.getStatus())) throw new IllegalArgumentException("Incorrect status");
        dao.update(entity);
        return entity;
    }

    @Override
    public void remove(Long id) {
        dao.remove(id);
    }
}
