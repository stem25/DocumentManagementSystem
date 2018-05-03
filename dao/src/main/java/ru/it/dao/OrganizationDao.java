package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.model.Organization;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class OrganizationDao extends AbstractDao<Organization> {

    private static Logger logger = LoggerFactory.getLogger(OrganizationDao.class);
    private static String BASE_SQL = "SELECT org FROM Organization org";

    @Override
    public Organization read(Long id) {
        return entityManager.find(Organization.class, id);
    }

    @Override
    public List<Organization> list(Map<String, String> filter) {
        StringBuilder sql = new StringBuilder(BASE_SQL);
        buildWhere(sql, filter);
        buildOrderBy(sql, filter, "org");
        TypedQuery<Organization> typedQuery = entityManager.createQuery(sql.toString(), Organization.class);
        buildPaging(typedQuery, filter);
        return typedQuery.getResultList();
    }

    @Override
    public void update(Organization entity) {
        entityManager.merge(entity);
    }

    @Override
    public Long create(Organization entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    protected void buildWhere(StringBuilder sql, Map<String, String> filter) {
        sql.append(" WHERE 1=1\n");
        if(filter.containsKey("id")){
            sql.append(" AND org.id = ").append(filter.get("id"));
        }
        if(filter.containsKey("name")){
            sql.append(" AND org.name LIKE '%").append(filter.get("name")).append("%'");
        }
        if(filter.containsKey("address")){
            sql.append(" AND org.address LIKE '%").append(filter.get("address")).append("%'");
        }
        if(filter.containsKey("legalAddress")){
            sql.append(" AND org.legalAddress LIKE '%").append(filter.get("legalAddress")).append("%'");
        }
    }
}
