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
    private static String BASE_SQL = "SELECT d FROM Department d LEFT JOIN d.head p LEFT JOIN d.organization o\n";

    @Override
    public Department read(Long id){
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        buildOrderBy(sql, filter, "d");
        TypedQuery<Department> typedQuery = entityManager.createQuery(sql.toString(), Department.class);
        buildPaging(typedQuery, filter);
        return typedQuery.getResultList();
    }

    @Override
    public void update(Department entity) {
        entityManager.merge(entity);
    }

    @Override
    public Long create(Department entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    protected void buildWhere(StringBuilder sql, Map<String, String> filter) {
        sql.append(" WHERE 1=1\n");
        if(filter.containsKey("id")){
            sql.append(" AND d.id = ").append(filter.get("id"));
        }
        if(filter.containsKey("name")){
            sql.append(" AND d.name LIKE '%").append(filter.get("name")).append("%'");
        }
        if(filter.containsKey("address")){
            sql.append(" AND d.contacts LIKE '%").append(filter.get("contacts")).append("%'");
        }
        if(filter.containsKey("head_id")){
            sql.append(" AND p.id = ").append(filter.get("head_id"));
        }
        if(filter.containsKey("org_id")){
            sql.append(" AND o.id = ").append(filter.get("org_id"));
        }
    }
}
