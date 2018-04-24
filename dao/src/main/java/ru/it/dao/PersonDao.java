package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.Exception.NotFoundException;
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
    private static String BASE_SQL = "SELECT p FROM Person p \n";



    @Override
    public Person read(Long id){
        StringBuilder sql = new StringBuilder(BASE_SQL);
        sql.append(" WHERE p.id = ").append(id);
        List<Person> persons = entityManager.createQuery(sql.toString(), Person.class).getResultList();
        if(persons.isEmpty()) throw new NotFoundException();
        return persons.get(0);
    }

    @Override
    public List<Person> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        TypedQuery<Person> typedQuery = entityManager.createQuery(sql.toString(), Person.class);
        List<Person> list = typedQuery.getResultList();
        return list;
    }

    @Override
    public void update(Person entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Long create(Person entity) {
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
        //FilterUtils.initFilter(filter,"p", sql);
        sql.append(" JOIN p.department d");
        sql.append(" WHERE 1=1 ");
        if(filter.containsKey("dep_id")) {
            sql
                    .append(" AND ")
                    .append("d.id = ")
                    .append(Long.valueOf(filter.get("dep_id")));
        }
    }


}
