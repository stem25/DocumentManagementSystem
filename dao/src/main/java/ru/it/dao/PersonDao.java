package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.model.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class PersonDao extends AbstractDao<Person> {

    private static Logger logger = LoggerFactory.getLogger(PersonDao.class);
    private static String BASE_SQL = "SELECT p FROM Person p JOIN p.department dep\n";

    @Override
    public Person read(Long id){
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        buildOrderBy(sql, filter, "p");
        TypedQuery<Person> typedQuery = entityManager.createQuery(sql.toString(), Person.class);
        buildPaging(typedQuery, filter);
        return typedQuery.getResultList();
    }

    @Override
    public void update(Person entity) {
        entityManager.merge(entity);
    }

    @Override
    public Long create(Person entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void buildWhere(StringBuilder sql, Map<String, String> filter) {
        sql.append(" WHERE 1=1\n");
        if(filter.containsKey("id")){
            sql.append(" AND p.id = ").append(filter.get("id"));
        }
        if(filter.containsKey("firstName")){
            sql.append(" AND p.firstName LIKE '%").append(filter.get("firstName")).append("%'");
        }
        if(filter.containsKey("secondName")){
            sql.append(" AND p.secondName LIKE '%").append(filter.get("secondName")).append("%'");
        }
        if(filter.containsKey("lastName")){
            sql.append(" AND p.lastName LIKE '%").append(filter.get("lastName")).append("%'");
        }
        if(filter.containsKey("position")){
            sql.append(" AND p.position LIKE '%").append(filter.get("position")).append("%'");
        }
        if(filter.containsKey("dep_id")){
            sql.append(" AND dep.id = ").append(filter.get("dep_id"));
        }
    }


}
