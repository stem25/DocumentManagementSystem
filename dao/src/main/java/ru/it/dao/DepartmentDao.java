package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.Exception.NotFoundException;
import ru.it.model.Department;
import ru.it.model.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class DepartmentDao extends AbstractDao<Department> {

    private static Logger logger = LoggerFactory.getLogger(DepartmentDao.class);
    private static String BASE_SQL = "SELECT d FROM Department d JOIN d.organization o\n";



    @Override
    public Department read(Long id){
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        TypedQuery<Department> typedQuery = entityManager.createQuery(sql.toString(), Department.class);
        List<Department> list = typedQuery.getResultList();
        return list;
    }

    @Override
    public void update(Department entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Long create(Department entity) {
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
        sql.append(" WHERE 1=1 ");
        if(filter.containsKey("org_id")) {
            sql
                    .append(" AND ")
                    .append("o.id = ")
                    .append(Long.valueOf(filter.get("org_id")));
        }
    }


}
