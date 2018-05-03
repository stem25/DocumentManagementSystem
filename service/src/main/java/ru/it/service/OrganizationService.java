package ru.it.service;

import ru.it.dao.OrganizationDao;
import ru.it.model.Organization;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class OrganizationService implements Crud<Organization> {

    @EJB
    private OrganizationDao dao;


    @Override
    public Organization create(Organization entity) {
        Long id = dao.create(entity);
        return dao.read(id);
    }

    @Override
    public Organization read(Long id) {
        return dao.read(id);
    }

    @Override
    public List<Organization> list(Map<String, String> filter) {
        return dao.list(filter);
    }

    @Override
    public Integer count(Map<String, String> filter) {
        return dao.count(filter);
    }

    @Override
    public Organization update(Organization entity) {
        dao.update(entity);
        return dao.read(entity.getId());
    }

    @Override
    public void remove(Long id) {
        dao.remove(id);
    }
}
