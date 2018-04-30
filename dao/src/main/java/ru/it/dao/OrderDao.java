package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.model.Order;
import ru.it.model.Order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class OrderDao extends AbstractDao<Order> {

    private static Logger logger = LoggerFactory.getLogger(OrderDao.class);
    private static String BASE_SQL = "SELECT o FROM Order o \n";



    @Override
    public Order read(Long id){
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        TypedQuery<Order> typedQuery = entityManager.createQuery(sql.toString(), Order.class);
        List<Order> list = typedQuery.getResultList();
        return list;
    }

    @Override
    public void update(Order entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Long id) {
        Order order = read(id);
        entityManager.remove(order);
    }

    @Override
    public Long create(Order entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public void buildWhere(StringBuilder sql, Map<String, String> filter) {

    }


}
