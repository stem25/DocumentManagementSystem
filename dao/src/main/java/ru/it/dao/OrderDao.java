package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.model.Order;
import ru.it.model.Order;
import ru.it.model.Organization;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class OrderDao extends AbstractDao<Order> {

    private static Logger logger = LoggerFactory.getLogger(OrderDao.class);
    private static String BASE_SQL = "SELECT o FROM Order o JOIN o.author p \n";

    @Override
    public Order read(Long id){
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        buildOrderBy(sql, filter, "o");
        TypedQuery<Order> typedQuery = entityManager.createQuery(sql.toString(), Order.class);
        buildPaging(typedQuery, filter);
        return typedQuery.getResultList();
    }

    @Override
    public void update(Order entity) {
        entityManager.merge(entity);
    }

    @Override
    public Long create(Order entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void buildWhere(StringBuilder sql, Map<String, String> filter) {
        sql.append(" WHERE 1=1\n");
        if(filter.containsKey("id")){
            sql.append(" AND o.id = ").append(filter.get("id"));
        }
        if(filter.containsKey("deadline")){
            sql.append(" AND o.deadline = ").append(filter.get("deadline"));
        }
        if(filter.containsKey("subject")){
            sql.append(" AND o.subject LIKE '%").append(filter.get("subject")).append("%'");
        }
        if(filter.containsKey("address")){
            sql.append(" AND o.description LIKE '%").append(filter.get("description")).append("%'");
        }
        if(filter.containsKey("author")){
            sql.append(" AND p.id = ").append(filter.get("author"));
        }
        if(filter.containsKey("executor")){
            sql.append(" AND p.id = ").append(filter.get("executor"));
        }
        if(filter.containsKey("status")){
            sql.append(" AND o.status = ").append(filter.get("status"));
        }
    }


}
