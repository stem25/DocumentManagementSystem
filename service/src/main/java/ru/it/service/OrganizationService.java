package ru.it.service;

import ru.it.dao.OrganizationDao;
import ru.it.dao.PersonDao;
import ru.it.model.Organization;
import ru.it.model.Person;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class OrganizationService implements Crud<Organization> {

    @EJB
    private OrganizationDao organizationDao;


    @Override
    public Organization create(Organization entity) {
        return null;
    }

    @Override
    public Organization read(Long id) {
        return null;
    }

    @Override
    public List<Organization> list(Map<String, String> filter) {
        return null;
    }

    @Override
    public Organization update(Organization entity) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
