package ru.it.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.it.Exception.NotFoundException;
import ru.it.model.Organization;
import ru.it.model.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.HashMap;
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
        TypedQuery<Organization> typedQuery = entityManager.createQuery(sql.toString(), Organization.class);
        List<Organization> list = typedQuery.getResultList();
        return list;
    }

    @Override
    public void update(Organization entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Long create(Organization entity) {
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
        FilterUtils.initFilter(filter,"org", sql);
    }


}
