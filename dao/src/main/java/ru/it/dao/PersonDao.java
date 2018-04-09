package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.model.Person;


import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class PersonDao extends AbstractDao<Person> {

    private static Logger logger = LoggerFactory.getLogger(PersonDao.class);
    private static String BASE_SQL = "SELECT p FROM Person p";

    @Override
    public Person read(Long id) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        sql.append(" WHERE p.id = ").append(id);
        return entityManager.createQuery(sql.toString(), Person.class).getResultList().get(0);
    }

    @Override
    public List<Person> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        FilterUtils.initFilter(filter, "p", sql);
        return entityManager.createQuery(sql.toString(), Person.class).getResultList();
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
        return entity.getId();
    }

    @Override
    public Integer count() {
        return null;
    }


}
